package stepDefinitions;

import cucumber.api.java.pt.Dado;
import cucumber.api.java.pt.Então;
import cucumber.api.java.pt.Quando;
import org.springframework.beans.factory.annotation.Autowired;
import pageObjects.CadastroPage;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by assisjrs on 04/04/17.
 */
public class  InserçãoDeUsuariosSteps {
    @Autowired
    private CadastroPage cadastroPage;

    @Dado("^que eu tenha o nome do usuário como \"([^\"]*)\" e o email como \"([^\"]*)\"$")
    public void queEuTenhaONomeDoUsuárioComoEOEmail(final String nome, final String email) {
        cadastroPage.setNome(nome);
        cadastroPage.setEmail(email);
    }

    @Quando("^eu insiro o usuário$")
    public void euInsiroOUsuário() throws InterruptedException {
        cadastroPage.acessar();
        cadastroPage.novoUsuario();
    }

    @Então("^Deve exibir (\\d+) usuários na lista$")
    public void deveExibirNaLista(int usuariosCadastrados) throws InterruptedException{
        assertThat(cadastroPage.getUsuarios().size()).isEqualTo(usuariosCadastrados);
    }
}
