public enum Images{
    TRASH1("images/trash1.png"),
    TRASH2("images/trash2.png"),
    TRASH3("images/trash3.png"),
    TRASH4("images/trash4.png"),
    TRASH5("images/trash5.png"),
    INVA1("images/creature1.png"),
    INVA2("images/creature2.png"),
    CRAB("images/crab.png");

    private String name = null;
    private Images(String s){
        name = s;
    }
    public String getName(){
        return name;
    }
} /* This file is because otherwise
we would have to bring the View into the Model and
nobody wants to have to deal with that */