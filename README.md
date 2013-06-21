jdonmvc
=======

java web framework working on jdon and spring


java example:
-----------------------
      public class TestController {

    private static DB db = new DB();

    @In
    private RequestBody body;

    @Path("/")
    public Represent index() {
        return new Html("index");
    }

    @Path("/users")
    public Represent get() {
        return Json.create(db.all());
    }

    @Path("post:/users")
    public Represent add(User user) {
        db.add(user);
        return Json.create(new Result(true, "add success"));
    }

    @Path("put:/users/:id")
    public Represent update(int id) throws IOException {
        db.update(body.json2Object(User.class));
        return Json.create(new Result(true, "update successful"));
    }

    @Path("delete:/users/:id")
    public Represent delete(int id) {
        db.del(id);
        return Json.create(db.all());
    }

    @Path("post:/upload")
    public Represent upload(FormFile formFile) {
        return Json.create(new Result(true, "upload successful"));
    }


groovy example:
-----------------------


    @Path("/forum/:id")
    void show(int id) {
        println id
    }
