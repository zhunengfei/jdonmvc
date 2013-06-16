jdonmvc
=======

java web framework working on jdon and spring


java example:
-----------------------

    public class Controller {

        @In
        private HttpServletRequest request;
        
        @In
        private UserService userService;


        @Path("/blog/:id")
        @GET
        public void show(int id) {
            System.out.println(id);
        }
        
        @Path("/upload")
        @POST
        public void upload(FormFile formFile) {
           
        }
    }


groovy example:
-----------------------


    @Path("/forum/:id")
    void show(int id) {
        println id
    }
