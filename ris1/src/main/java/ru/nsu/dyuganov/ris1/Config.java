package ru.nsu.dyuganov.ris1;

public class Config {
    private boolean isArchive = false;
    private String path;

    public Config(String path, boolean isArchive){
        this.path = path;
        this.isArchive = isArchive;
    }

    public boolean isArchive(){
        return isArchive;
    }

    public String getPath() {
        return path;
    }
}




