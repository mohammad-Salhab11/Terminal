import java.io.File;
import java.io.IOException;

public class ShellCommandHandler {
    private File currentDirectory;

    public ShellCommandHandler() {
        currentDirectory = new File(System.getProperty("user.dir"));
    }

    public void printWorkingDirectory() {
        System.out.println(currentDirectory.getAbsolutePath());
    }

    public void listDirectory() {
        File[] files = currentDirectory.listFiles();
        if (files != null) {
            for (int i = 0; i < files.length; i++) {
                File file = files[i];
                System.out.println(file.getName());
            }
        } else {
            System.out.println("Unable to list directory contents.");
        }
    }

    public void changeDirectory(String name) {
        File newDir;
        if (name.equals("..")) {
            newDir = currentDirectory.getParentFile();
        } else {
            newDir = new File(currentDirectory, name);
        }
        if (newDir != null && newDir.exists() && newDir.isDirectory()) {
            currentDirectory = newDir;
        } else {
            System.out.println("Directory does not exist.");
        }
    }

    public void makeDirectory(String name) {
        File newDir = new File(currentDirectory, name);
        if (newDir.exists()) {
            System.out.println("Directory already exists.");
        } else {
            boolean created = newDir.mkdir();
            if (!created) {
                System.out.println("Failed to create directory.");
            }
        }
    }

    public void createFile(String name) {

        File newFile = new File(currentDirectory, name);
        if (!newFile.exists()) {
            try {
                if (newFile.createNewFile()) {
                    System.out.println("The file was created successfully.");
                } else {
                    System.out.println("Error creating the file.");
                }
            } catch (IOException e) {
                System.out.println("error :" + e.getMessage());
            }
        } else {
            System.out.println("The file already exists.");
        }
    }

    public void printHelp() {
        System.out.println("supported commands:");
        System.out.println("pwd , print current directory");
        System.out.println("ls , list files and directories");
        System.out.println("cd (directory) , change directory");
        System.out.println("mkdir (directory) , create a new directory");
        System.out.println("touch (filename) , Create a new file");
        System.out.println("help , show this help message");
        System.out.println("exit , exit the shell");
    }

    public File getCurrentDirectory() {
        return currentDirectory;
    }
}