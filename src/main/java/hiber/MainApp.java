package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context = 
            new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);

      userService.add(new User("User1", "Lastname1", "user1@mail.ru"));
      userService.add(new User("User2", "Lastname2", "user2@mail.ru"));
      userService.add(new User("User3", "Lastname3", "user3@mail.ru"));
      userService.add(new User("User4", "Lastname4", "user4@mail.ru"));

      List<User> users = userService.listUsers();
      for (User user : users) {
         System.out.println("Id = "+user.getId());
         System.out.println("First Name = "+user.getFirstName());
         System.out.println("Last Name = "+user.getLastName());
         System.out.println("Email = "+user.getEmail());
         System.out.println("_______________________");
      }

      User u1 = new User ("User1", "Lastname1", "user1@mail.ru");
      u1.setCar(new Car ("BMW i8", 11));
      userService.add(u1);

      User u2 = new User("User2", "Lastname2", "user2@mail.ru");
      u2.setCar(new Car ("Toyota camry", 22));
      userService.add(u2);

      User u3 = new User("User3", "Lastname3", "user3@mail.ru");
      u3.setCar(new Car ("Toyota corolla", 333));
      userService.add(u3);

      User u4 = new User("User4", "Lastname4", "user4@mail.ru");
      u4.setCar(new Car ("Lada granta", 4));
      userService.add(u4);

      List<User> users2 = userService.listUsers();
      for (User user : users2) {
         System.out.println("Id = "+user.getId());
         System.out.println("First Name = "+user.getFirstName());
         System.out.println("Last Name = "+user.getLastName());
         System.out.println("Email = "+user.getEmail());
         System.out.println("Car = " + user.getCar().getModel() + " " + user.getCar().getSeries());
         System.out.println("_______________________");
      }

      User usr = userService.getUserForCar("Toyota corolla", 333);
      System.out.println("Search: ");
      System.out.println("Id = "+usr.getId());
      System.out.println("First Name = "+usr.getFirstName());
      System.out.println("Last Name = "+usr.getLastName());
      System.out.println("Email = "+usr.getEmail());
      System.out.println("Car = " + usr.getCar().getModel() +" " +usr.getCar().getSeries());


      context.close();
   }
}
