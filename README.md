jdonmvc
=======

Java web framework working on jdon and spring

this framework's target is making web developing easy and simple, web programing just like FP, the process just is function
in this framework,you woude thking the controller as the set of function that is cloure when runing.

write controller like this:
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


run the demo
--------------------------

* install maven
* checkout the mvcdemo code, the address is [mvcdemo][]
* go to maven home, then input mvn jetty:run-exploded
* open your browser,go http://localhost:8080/mvcdemo/

[mvcdemo]:https://github.com/oojdon/mvcdemo


