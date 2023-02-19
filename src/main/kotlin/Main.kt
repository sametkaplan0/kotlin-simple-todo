import java.io.File
import kotlin.collections.*

fun main(){
    val todofile = File("todo.txt")
    while (true){
        print("\nPlease select the action you want to take:\n" +
                "1) List available To-Dos\n" +
                "2) Add a new To-Do\n" +
                "3) Delete a To-Do\n" +
                "4) Sign out\n" +
                ">  ")
        val input = readln().toIntOrNull() ?:1
        when(input){
            1 -> listTodos(todofile)
            2 -> addTodos(todofile)
            3 -> delTodos(todofile)
            4 -> {
                println("Successfully logged out!")
                return
            }
            else -> println("Invalid input!")
        }
    }

}

fun listTodos(todofile: File){
    if (!todofile.exists()) println("File does not exist")
    if (todofile.length() == 0L) println("No To-Do available!") else{
        val todos = todofile.readLines()
        println("List of To-Dos: ")
        for ((i, todo) in todos.withIndex()){
            println("${i+1})  $todo")
        }
    }
}

fun addTodos(todofile: File){
    if (!todofile.exists()) println("File does not exist!")
    print("\nType the To-Do you want to add\n>    ")
    val todo = readln() ?: return
    todofile.appendText("$todo\n")
    println("$todo successfully added!")
}

fun delTodos(todofile: File){
    if (!todofile.exists()) println("File does not exist!")
    if (todofile.length() == 0L) println("No To-Do available!")
    listTodos(todofile)
    print("\nEnter the number of the To-Do you want to delete.\n>   ")
    val input = readln().toInt() ?: return
    val lines = todofile.readLines().toMutableList()
    lines.removeAt(input-1)
    todofile.writeText(lines.joinToString("\n"))
}