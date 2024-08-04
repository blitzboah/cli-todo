import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Todo{
    private List<String> todoList;
    private static final String TODO_FILE = "todo.txt";

    public Todo(){
        todoList = new ArrayList<String>();
        load();
    }

    private void load() {
        try (BufferedReader reader = new BufferedReader(new FileReader(TODO_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                todoList.add(line);
            }
        } catch (IOException e) {
            System.out.println("No existing todo list found. Starting fresh!");
        }
    }

    private void save(){
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(TODO_FILE))){
            for(String task : todoList){
                writer.write(task);
                writer.newLine();
            }
        }
        catch(IOException e){
            e.getMessage();
        }
    }

    private void create(String task){
        todoList.add(task);
        save();
        System.out.println("task created!\n");
    }

    private void read(){
        if(todoList.isEmpty()){
            System.out.println("list is empty!\n");
        }
        else{
            System.out.println("+-------------------------------+");
            for(int i=0; i<todoList.size(); i++){
                System.out.println((i+1)+" > "+todoList.get(i));
            }
            System.out.println("+-------------------------------+\n");
        }
    }

    private void update(int index, String task){
        if(index >= 1 && index <= todoList.size()){
            todoList.set(index - 1, task);
            save();
        }
        else{
            System.out.println("invalid task no\n");
        }
        System.out.println("task updated!\n");
    }

    private void delete(int index){
        if(index >= 0 && index <= todoList.size()){
            todoList.remove(index - 1);
            save();
        }
        else{
            System.out.println("invalid task no\n");
        }
        System.out.println("task deleted!\n");
    }

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        Todo fun = new Todo();
        String taskString;
        int ch = 0;
        System.out.println("welcome to ur cli based todo!\n");
        while(ch != 5){
            System.out.println("1 > create task\n2 > read\n3 > update\n4 > delete\n5 > exit");
            System.out.println();
            ch = sc.nextInt();
            sc.nextLine();
            switch(ch){
                case 1 : System.out.println("write a task to add!");
                    taskString = sc.nextLine();
                    fun.create(taskString);
                    break;
                case 2 : fun.read();
                         break;
                case 3 : System.out.println("enter the task no to update!");
                    int up = sc.nextInt();
                    sc.nextLine();
                    System.out.println("enter the updated task!");
                    taskString = sc.nextLine();
                    fun.update(up, taskString);
                    break;
                case 4 : System.out.println("enter the task no to delete!");
                    int del = sc.nextInt();
                    sc.nextLine();
                    fun.delete(del);
                    break;
                case 5 : System.out.println("owari da!");
                    break;
                default : break;
            }
        }
    }
}