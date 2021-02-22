package pruebaNetflix;

import org.testng.annotations.DataProvider;

public class dataProvider {

    @DataProvider(name = "datosEmails")
    public Object[][] emails() {
        return new Object[][]{
                {"lelele@test.com"},
                {"jejejje@test.com"},
                {"teteete@test.com"}
        };
    }

    @DataProvider(name = "tagNames")
    public Object[][] tags() {
        return new Object[][]{
                {"H1"},
                {"x"},
                {"a"}
        };
    }
}
