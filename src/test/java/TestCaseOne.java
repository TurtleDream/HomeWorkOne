import library.BaseTest;
import org.json.simple.JSONObject;
import org.junit.Test;
import pages.RosgosstrakhPage;
import pages.VoluntaryMedicalInsurancePage;

public class TestCaseOne extends BaseTest {

    @Test
    public void signUp(){
        RosgosstrakhPage rosgosstrakhPage = new RosgosstrakhPage(webDriver);
        VoluntaryMedicalInsurancePage voluntaryMedicalInsurancePage = new VoluntaryMedicalInsurancePage(webDriver);
        JSONObject personalInfo = new JSONObject();
        personalInfo.put("lastName", "Иванов");
        personalInfo.put("firstName", "Иван");
        personalInfo.put("middleName", "Иванович");
        personalInfo.put("phoneNumber", "9876543210");
        personalInfo.put("email", "qwertyqwerty");
        personalInfo.put("comment", "Здесь могла быть ваша реклама");

        rosgosstrakhPage
                .goToPage()
                .goToVMI();

        voluntaryMedicalInsurancePage
                .checkTitle()
                .sendRequest();

        voluntaryMedicalInsurancePage.checkPageForText("Заявка на добровольное медицинское страхование");

        voluntaryMedicalInsurancePage.fillInTheForm(personalInfo);

        voluntaryMedicalInsurancePage.checkForm(personalInfo);
    }
}
