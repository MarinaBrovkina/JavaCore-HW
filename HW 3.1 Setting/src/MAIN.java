import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        StringBuilder sb = new StringBuilder();
        File mainSrcDirectory = new File("Games/src/main");
        sb.append(mainSrcDirectory.mkdirs() + "\n");
        File mainFileDirectory = new File("Games/src/main/Main.java");
        try {
            boolean creat = mainFileDirectory.createNewFile();
            if (creat)
                sb.append("Файл был создан: " + mainFileDirectory.getAbsolutePath() + "\n");
        } catch (IOException ex) {
            sb.append(ex.getMessage() + "\n");
        }
        File utilsFileDirectory = new File("Games/src/main/Utils.java");
        try {
            boolean creat = utilsFileDirectory.createNewFile();
            if (creat)
                sb.append("Файл был создан: " + utilsFileDirectory.getAbsolutePath() + "\n");
        } catch (IOException ex) {
            sb.append(ex.getMessage() + "\n");
        }
        File testDirectory = new File("Games/src/test");
        sb.append(testDirectory.mkdirs() + "\n");
        File drawablesResDirectory = new File("Games/res/drawables");
        sb.append(drawablesResDirectory.mkdirs() + "\n");
        File vectorsResDirectory = new File("Games/res/vectors");
        sb.append(vectorsResDirectory.mkdirs() + "\n");
        File iconsResDirectory = new File("Games/res/icons");
        sb.append(iconsResDirectory.mkdirs() + "\n");
        File tempDirectory = new File("Games/temp/");
        sb.append(tempDirectory.mkdir() + "\n");
        File savegamesDirectory = new File("Games/savegames");
        sb.append(savegamesDirectory.mkdir() + "\n");
        File tempFileDirectory = new File("Games/temp/temp.txt");
        try {
            boolean creat = tempFileDirectory.createNewFile();
            if (creat) {
                FileWriter writer = new FileWriter(tempFileDirectory);
                writer.write(sb.toString());
                writer.close();
                System.out.println("Лог был сохранен успешно: " + tempDirectory.getAbsolutePath());
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
