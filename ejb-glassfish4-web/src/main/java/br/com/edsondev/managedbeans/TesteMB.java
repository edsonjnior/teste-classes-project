package br.com.edsondev.managedbeans;

import br.com.edsondev.entities.Arquivo;
import br.com.edsondev.entities.InformacaoExtra;
import br.com.edsondev.entities.Teste;
import br.com.edsondev.enums.Segmento;
import br.com.edsondev.enums.Status;
import br.com.edsondev.enums.TipoArquivo;
import br.com.edsondev.repository.InformacaoExtraFacade;
import br.com.edsondev.repository.TesteFacade;
import br.com.edsondev.utils.FacesUtils;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.SdkClientException;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.PutObjectRequest;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.servlet.http.Part;

@Named
@ViewScoped
public class TesteMB implements Serializable {

    private static final long serialVersionUID = 1L;
    private static final Logger LOG = Logger.getLogger(TesteMB.class.getName());

    @EJB
    private TesteFacade testeFacade;
    @EJB
    private InformacaoExtraFacade informacaoExtraFacade;

    private List<Teste> testes;
    private Teste teste;

    private String testeId;
    private String messageSeverity;

    private Part arquivoUpload;

    private String tipoArquivo;

    private List<Arquivo> roteirosTeste = new ArrayList();
    private List<Arquivo> resultadosTeste = new ArrayList();
    private Set<InformacaoExtra> textos;

    private String etapa;
    private String ano;
    private String textoExtra;

    private Map<String, List<String>> etapasAno = FacesUtils.getEtapasAno();
    private List<String> anosEtapa;

    public TesteMB() {
	novoTeste();

	// classe css para customização do painel de mensagens
	messageSeverity = "primary";

	// Recebendo o id da sala como parâmetro para edição
	FacesContext fc = FacesContext.getCurrentInstance();
	Map<String, String> params = fc.getExternalContext().getRequestParameterMap();
	testeId = params.get("testeId");
    }

    @PostConstruct
    void init() {
    }

    public void novoTeste() {
	teste = new Teste();
    }

    public void salvarArquivo(String tipoArquivo) {
	TipoArquivo tipo = TipoArquivo.valueOf(tipoArquivo);
	salvarArquivoTeste(tipo);
    }

    private void salvarArquivoTeste(TipoArquivo tipoArquivo) {
	Arquivo arquivo = new Arquivo();
	StringBuilder sb = new StringBuilder("/uploads/");

	String extArquivo = arquivoUpload.getContentType().split("\\/")[1];

	arquivo.setTipo(tipoArquivo);
	String tipoCaminho = tipoArquivo.equals(TipoArquivo.ROTEIRO) ? "roteiro_teste_" : "resultado_teste_";
	sb.append(tipoCaminho).append(teste.getId()).append("_").append(ano).append("_").append(etapa).append(".")
		.append(extArquivo);

	arquivo.setCaminho(sb.toString());
	arquivo.setEtapa(etapa);
	arquivo.setAno(ano);
	arquivo.setTeste(teste);

	teste.getArquivos().add(arquivo);

    }

    /**
     *
     * @param fileToUpload
     *            Arquivo
     * @param fileName
     *            Nome do arquivo no S3 (key)
     * @return URL do arquivo enviado
     */
    public String salvarArquivoS3(File fileToUpload, String fileName) {
	try {
	    Properties props = new Properties();
	    props.load(getClass().getResourceAsStream("/s3.properties"));

	    AWSCredentials credentials = new BasicAWSCredentials(props.getProperty("AWSAccessKeyId"),
		    props.getProperty("AWSSecretKey"));
	    String bucketName = props.getProperty("bucketName");

	    AmazonS3Client s3Client = (AmazonS3Client) AmazonS3ClientBuilder.standard().withRegion(Regions.SA_EAST_1)
		    .withCredentials(new AWSStaticCredentialsProvider(credentials)).build();
	    s3Client.putObject(new PutObjectRequest(bucketName, fileName, fileToUpload)
		    .withCannedAcl(CannedAccessControlList.PublicRead));

	    return s3Client.getResourceUrl(bucketName, fileName);
	} catch (AmazonServiceException ex) {
	    LOG.log(Level.SEVERE, null, ex);
	} catch (SdkClientException ex) {
	    LOG.log(Level.SEVERE, null, ex);
	} catch (IOException ex) {
	    LOG.log(Level.SEVERE, null, ex);
	}

	return null;
    }

    public void salvarTeste() {
	try {
	    if (teste.getId() != null) {
		testeFacade.edit(teste);
	    } else {
		testeFacade.create(teste);
	    }
	} catch (Exception ex) {
	    LOG.log(Level.SEVERE, null, ex);
	}
    }

    public void adicionarTexto() {
	if (teste.getTextos() == null)
	    teste.setTextos(new HashSet());

	teste.getTextos().add(textoExtra);

	textoExtra = null;
    }

    public Teste getTeste() {
	return teste;
    }

    public void setTeste(Teste teste) {
	this.teste = teste;
    }

    public void listarTodos() {
	testes = testeFacade.findAll();
    }

    public List<Teste> getTestes() {
	return testes;
    }

    public void setTestes(List<Teste> testes) {
	this.testes = testes;
    }

    public Status[] getStatusTeste() {
	return Status.values();
    }

    public Segmento[] getSegmentosTeste() {
	return Segmento.values();
    }

    public String getTesteId() {
	return testeId;
    }

    public void setTesteId(String testeId) {
	this.testeId = testeId;
    }

    public String getMessageSeverity() {
	return messageSeverity;
    }

    public void setMessageSeverity(String messageSeverity) {
	this.messageSeverity = messageSeverity;
    }

    public Part getArquivoUpload() {
	return arquivoUpload;
    }

    public void setArquivoUpload(Part arquivoUpload) {
	this.arquivoUpload = arquivoUpload;
    }

    public String getEtapa() {
	return etapa;
    }

    public void setEtapa(String etapa) {
	this.etapa = etapa;
    }

    public String getAno() {
	return ano;
    }

    public void setAno(String ano) {
	this.ano = ano;
    }

    public List<Arquivo> getRoteirosTeste() {
	if (!teste.getArquivos().isEmpty()) {
	    roteirosTeste = teste.getArquivos().stream().filter(a -> a.getTipo().equals(TipoArquivo.ROTEIRO))
		    .collect(Collectors.toList());
	} else if (!roteirosTeste.isEmpty()) {
	    return new ArrayList();
	}

	return roteirosTeste;
    }

    public void setRoteirosTeste(List<Arquivo> roteirosTeste) {
	this.roteirosTeste = roteirosTeste;
    }

    public List<Arquivo> getResultadosTeste() {
	if (!teste.getArquivos().isEmpty()) {
	    resultadosTeste = teste.getArquivos().stream().filter(a -> a.getTipo().equals(TipoArquivo.RESULTADO))
		    .collect(Collectors.toList());
	} else if (!resultadosTeste.isEmpty()) {
	    return new ArrayList();
	}

	return resultadosTeste;
    }

    public void setResultadosTeste(List<Arquivo> resultadosTeste) {
	this.resultadosTeste = resultadosTeste;
    }

    public void carregarAnos() {
	anosEtapa = null;

	if (etapa != null) {
	    anosEtapa = etapasAno.get(etapa);
	}
    }

    public List<String> getEtapas() {
	return etapasAno.entrySet().stream().map(Map.Entry::getKey).collect(Collectors.toList());
    }

    public List<String> getAnosEtapa() {
	return anosEtapa;
    }

    public String getTipoArquivo() {
	return tipoArquivo;
    }

    public void setTipoArquivo(String tipoArquivo) {
	this.tipoArquivo = tipoArquivo;
    }

    public String getTextoExtra() {
	return textoExtra;
    }

    public void setTextoExtra(String textoExtra) {
	this.textoExtra = textoExtra;
    }

    public Set<InformacaoExtra> getTextos() {
	if (textos == null) {
	    textos = new HashSet<>(informacaoExtraFacade.findAll());
	}

	return textos;
    }

    public void setTextos(Set<InformacaoExtra> textos) {
	this.textos = textos;
    }

    public List<String> textosLista() {
	return new ArrayList(teste.getTextos());
    }
}
