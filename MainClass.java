import controllers.AppController;

public class MainClass{

    //window dimensions
    static int windowWidth = 650;
    static int windowHeight = 750;

    //app controller
    private static AppController controller;

    //entry point of game application
    public static void main(String[] args){
        //initialize app controller with specified dimensions
        controller = new AppController(windowWidth,windowHeight);

        //make the defined window visible
        controller.frame.setVisible(true);
    }

}