package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.AbstractList;

import static org.openqa.selenium.By.tagName;
import static org.openqa.selenium.By.xpath;

public class UsuariosPageElement extends AbstractList<WebElement>{

    private WebDriver driver;
    private WebDriverWait wait;

    @FindBy(id = "dataTable_data")
    private WebElement dataTable_data;

    public UsuariosPageElement(final WebDriver driver){
        this.driver = driver;
        wait = new WebDriverWait(driver, 60);
    }

    @Override
    public int size() {
        driver.get("http://localhost:9090/index.xhtml");

        return dataTable_data.findElements(tagName("tr")).size();
    }

    @Override
    public WebElement get(int i) {
        driver.get("http://localhost:9090/index.xhtml");

        return dataTable_data.findElements(tagName("tr")).get(i);
    }

    public WebElement getRowBy(final String usuario) {
        driver.get("http://localhost:9090/index.xhtml");

        for (final WebElement tr: this) {
            for (final WebElement td: tr.findElements(xpath("td[2]"))) {
                final String text = td.getText();

                if(usuario.equalsIgnoreCase(text))
                    return td;
            }
        }

        return null;
    }
}