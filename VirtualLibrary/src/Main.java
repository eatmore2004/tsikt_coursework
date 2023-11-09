import BLL.BookService;
import BLL.UserService;
import BLL_Abstractions.IBookService;
import BLL_Abstractions.IUserService;
import Core.Models.Result;
import Core.Models.User;
import DAL.Repository.Repository;

public class Main {
    public static void main(String[] args){
        IUserService service = new UserService(new Repository(User.class));
//        Result<String> result = service.registerUser("John", "Doe", "johndoe2", "johndoe",
//                "Johndoe123");
//        if (result.getSuccess()) System.out.println("User registered successfully");
//        else System.out.println(result.getMessage());
        Result<User> userResult = service.loginUser("johndoe", "Johndoe123");
        if (userResult.getSuccess()) System.out.println("User logged in successfully " + userResult.getData().getUsername());
        else System.out.println(userResult.getMessage());
    }
}
