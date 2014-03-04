why this framework
=======

working on jdon and spring.
jdon is a DDD(domain driven design) framework

this framework's target is making web developing easy and simple.

web programing just like FP, the process just is function.

in this framework,you woude thking the controller as the set of function that is cloure when runing.

Now,Write controller
-----------------------
    public class Controller {

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

* install git,jdk
* install maven
* git clone https://github.com/oojdon/mvcdemo.git
* cd mvcdemo, then input mvn jetty:run-exploded
* baby go http://localhost:9090/mvcdemo/


