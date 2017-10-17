package stepDefinitions;

import config.DBUnit;
import cucumber.api.java.Before;
import cucumber.api.java.pt.Dado;
import cucumber.api.java.pt.Então;
import cucumber.api.java.pt.Quando;
import org.assertj.db.type.Request;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import pageObjects.CadastroPage;

import static config.DBUnit.dbUnitDatabaseConnection;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.db.api.Assertions.assertThat;

/**
 * Created by assisjrs on 04/04/17.
 */
public class ListaDeUsuariosSteps {
    private DBUnit dbUnit = new DBUnit();

    private Request usuarios;

    private WebDriver driver;
    private CadastroPage cadastroPage;

    @Before
    public void before() {
        driver = new ChromeDriver();
        cadastroPage =  new CadastroPage(driver);
    }

    @Dado("^exista o usuário \"([^\"]*)\" cadastrado$")
    public void existaOUsuárioCadastrado(String arg0) throws Throwable {
        dbUnit.cleanInsert("VisualizarTodosOsOutrosUsuarios.xml");
    }

    @Quando("^é exibida a lista de usuários$")
    public void éExibidaAListaDeUsuários() {}

    @Dado("^que deve-se sempre ter o administrador cadastrado$")
    public void queDeveSeSempreTerOAdministradorCadastrado() throws Throwable {
        dbUnit.cleanInsert("EncontrarUsuarioPorEmail.xml");
    }

    @Quando("^pesquisa-se pelo email \"([^\"]*)\"$")
    public void sePesquisaPeloEmail(String email) {
        usuarios = new Request(dbUnitDatabaseConnection(), "select nome from usuario where email = ?");
        usuarios.setParameters(email);
    }

    @Então("^o email deve estar associado ao usuário \"([^\"]*)\"$")
    public void oEmailDeveEstarAssociadoAoUsuário(String usuario) {
        assertThat(usuarios).column("nome")//
                .value().isEqualTo(usuario);
    }

    @Então("^a lista deve conter o usuário \"([^\"]*)\"$")
    public void aListaDeveConterOUsuário(String usuario) {
        final WebElement usuarioNaTabela = cadastroPage.getUsuarios().getRowBy(usuario);

        assertThat(usuarioNaTabela).isNotNull();
    }
}
