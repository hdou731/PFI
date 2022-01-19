import com.fasterxml.jackson.annotation.JsonAutoDetect;
import io.javalin.http.Handler;
import java.util.HashMap;
import java.util.Objects;
import java.util.Random;

public class OfferController {
  public static HashMap<String, Double> offers = new HashMap<>();
  public static HashMap<String, Double> offers2 = new HashMap<>();


  public static Handler getOffer = ctx -> {
    double amount = Double.parseDouble(Objects.requireNonNull(ctx.pathParam("amount")));
    double offerAmount = 0.9 * amount;
    String token = getRandomToken();

    offers.put(token, offerAmount);
    ctx.json(new Offer(token, offerAmount));
  };

  public static Handler getOffer2 = ctx -> {
    double amount = Double.parseDouble(Objects.requireNonNull(ctx.pathParam("amount")));
    double offerAmount = 0.95 * amount;
    String token = getRandomToken();

    offers2.put(token, offerAmount);
    ctx.json(new Offer(token, offerAmount));
  };

  public static Handler executeOffer = ctx -> {
    String token = Objects.requireNonNull(ctx.pathParam("token"));
    Objects.requireNonNull(offers.get(token));

    ctx.result("EXECUTED 0.9");
  };

  public static Handler executeOffer2 = ctx -> {
    String token = Objects.requireNonNull(ctx.pathParam("token"));
    Objects.requireNonNull(offers2.get(token));

    ctx.result("EXECUTED 0.95");
  };

  private static String getRandomToken() {
    // create a string of uppercase and lowercase characters and numbers
    String upperAlphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    String lowerAlphabet = "abcdefghijklmnopqrstuvwxyz";
    String numbers = "0123456789";

    // combine all strings
    String alphaNumeric = upperAlphabet + lowerAlphabet + numbers;

    // create random string builder
    StringBuilder sb = new StringBuilder();

    // create an object of Random class
    Random random = new Random();

    // specify length of random string
    int length = 10;

    for(int i = 0; i < length; i++) {

      // generate random index number
      int index = random.nextInt(alphaNumeric.length());

      // get character specified by index
      // from the string
      char randomChar = alphaNumeric.charAt(index);

      // append the character to string builder
      sb.append(randomChar);
    }
    return sb.toString();
  }

  @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
  public static class Offer {
    String token;
    Double amount;

    Offer(String token, Double amount) {
      this.token = token;
      this.amount = amount;
    }
  }
}
