package cn.qfys521.xiaoming.plugin;

import net.kronos.rkon.core.Rcon;
import net.kronos.rkon.core.ex.AuthenticationException;


import java.io.IOException;
import java.util.Objects;
import java.util.Scanner;

public class test {
    public static void main(String[] args) throws AuthenticationException, IOException {
        Rcon rcon = new Rcon("127.0.0.1",25575, "114514".getBytes());
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()){
            if (Objects.equals(scanner.nextLine(), "Exit")){
                rcon.disconnect();
                break;
            }
           try {
               String result = rcon.command(scanner.nextLine());
               System.out.println(result);
           }catch (Throwable throwable){
               System.out.println(throwable);
           }

        }

    }
}
