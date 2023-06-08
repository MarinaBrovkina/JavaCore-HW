import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

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
        File savegamesDirectory = new File("Games/saveGames");
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

        GameProgress gameProgress1 = new GameProgress(3, 5, 4, 5.5);
        GameProgress gameProgress2 = new GameProgress(7, 6, 9, 9.5);
        GameProgress gameProgress3 = new GameProgress(9, 2, 3, 2.5);

        saveGame("C:/Users/brovkmar/IdeaProjects/Lern/Games/saveGames/save1.dat", gameProgress1);
        saveGame("C:/Users/brovkmar/IdeaProjects/Lern/Games/saveGames/save2.dat", gameProgress2);
        saveGame("C:/Users/brovkmar/IdeaProjects/Lern/Games/saveGames/save3.dat", gameProgress3);

        List<String> filesToZip = new ArrayList<>();
        filesToZip.add("C:/Users/brovkmar/IdeaProjects/Lern/Games/saveGames/save1.dat");
        filesToZip.add("C:/Users/brovkmar/IdeaProjects/Lern/Games/saveGames/save2.dat");
        filesToZip.add("C:/Users/brovkmar/IdeaProjects/Lern/Games/saveGames/save3.dat");
        zipFiles("C:/Users/brovkmar/IdeaProjects/Lern/Games/saveGames/archive.zip", filesToZip);
        deleteUnusedSaveFiles(filesToZip);
    }

    public static void saveGame(String filePath, GameProgress gameProgress) {
        try (FileOutputStream fos = new FileOutputStream(filePath);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(gameProgress);
            System.out.println("Игра сохранена в " + filePath);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static void zipFiles(String zipFilePath, List<String> filesToZip) {
        try (FileOutputStream fos = new FileOutputStream(zipFilePath);
             ZipOutputStream zos = new ZipOutputStream(fos)) {
            for (String filePath : filesToZip) {
                File file = new File(filePath);
                if (!file.exists()) {
                    continue;
                }
                try (FileInputStream fis = new FileInputStream(file)) {
                    ZipEntry zipEntry = new ZipEntry(file.getName());
                    zos.putNextEntry(zipEntry);
                    byte[] buffer = new byte[1024];
                    int bytesRead;
                    while ((bytesRead = fis.read(buffer)) != -1) {
                        zos.write(buffer, 0, bytesRead);
                    }
                    zos.closeEntry();
                }
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static void deleteUnusedSaveFiles(List<String> filesToZip) {
        File savegamesDir = new File("C:/Users/brovkmar/IdeaProjects/Lern/Games/saveGames");
        File[] saveFiles = savegamesDir.listFiles();
        for (File file : saveFiles) {
            if (file.isFile() && !filesToZip.contains(file.getPath())&& !file.getName().endsWith(".zip")) {
                if (file.delete()) {
                    System.out.println("Файл удален " + file.getName());
                } else {
                    System.out.println("Ошибка удаления файла " + file.getName());
                }
            }
        }
    }
}