package graphics_view.view;

import javafx.scene.image.Image;

import java.util.Random;

public class Captcha {
    private static final String[] captchas =
            {"1181", "1381", "1491", "1722","1959",
                    "2163", "2177", "4310", "4681",
                    "5463", "5771", "6426", "6601",
                    "6960", "3847", "7415", "7609",
                    "8003", "8555", "8692", "9386"};

    private static String getRandomValue() {
        Random random = new Random(1000);

        return captchas[ random.nextInt(captchas.length)];
    }

    private String value;
    private Image captcha;

    public Captcha() {
        refresh();
    }

    public void refresh() {
        value = getRandomValue();
        captcha = new Image(Captcha.class.getResource("/assets/captcha/" + value + ".png").toExternalForm());
    }

    public String getValue() {
        return value;
    }

    public Image getCaptcha() {
        return captcha;
    }
}