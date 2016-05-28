import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.SlickException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
    public static void main(String[] args) throws SlickException {
        ApplicationContext context = new ClassPathXmlApplicationContext("context.xml");
        AppGameContainer bean = context.getBean(AppGameContainer.class);
        bean.start();
    }
}
