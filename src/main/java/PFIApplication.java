import io.javalin.Javalin;

// figure out the approach we want to take for config files
//  - json config file vs. a properties file
// figure out what database we want to use
//

public class PFIApplication {
  public static void main(String[] args) {
    Javalin app = Javalin.create().start(9002);

    app.get("/", ctx -> ctx.result("hello world"));

    app.get("/getOffer/{amount}", OfferController.getOffer);

    app.get("/executeOffer/{token}", OfferController.executeOffer);


    Javalin app2 = Javalin.create().start(9003);

    app2.get("/", ctx -> ctx.result("hello world 2"));

    app2.get("/getOffer/{amount}", OfferController.getOffer2);

    app2.get("/executeOffer/{token}", OfferController.executeOffer2);
  }
}