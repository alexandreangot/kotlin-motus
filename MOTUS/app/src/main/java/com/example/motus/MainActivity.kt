package com.example.motus

import MyGridAdapter
import android.os.Bundle
import android.widget.*
import android.widget.TextView.BufferType
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.get
import androidx.databinding.BindingAdapter


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.word)

        val motus = Motus()

        val myGridView = findViewById<GridView>(R.id.gridView)
        myGridView.numColumns = motus.getWord().length
        val adapter = MyGridAdapter(this, motus.getGrid())
        myGridView.adapter = adapter

        val keys = createKeyboard()

        for (key in keys){
            key.setOnClickListener {
                if (key.contentDescription == "❌"){
                    motus.removeLetter()
                }
                else{
                    motus.addLetter(key.contentDescription as String)
                }
                adapter.updateData(motus.getGrid())
            }
        }

    }

    /*
    private fun colorBoxes(word : String, row : MutableList<TextView>){
        for (i in word.indices){
            if(word[i].toString() == row[i].text){
                row[i].setBackgroundColor(Color.RED)
            }
            else{
                for (j in word.indices){
                    if((word[j].toString() == row[i].text) && (word[i].toString() != word[j].toString())){
                        row[i].setBackgroundColor(Color.GREEN)
                    }
                }
            }
        }
    }
    */
    private fun createKeyboard(): MutableList<Button> {
        val buttonList = mutableListOf<Button>()
        val tableLayout = findViewById<TableLayout>(R.id.tableLayoutKeyboard)

        val alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray()
        var index = 0

        for (i in 0 until 3) {
            val tableRow = TableRow(this)
            tableRow.layoutParams = TableLayout.LayoutParams(
                TableLayout.LayoutParams.MATCH_PARENT,
                TableLayout.LayoutParams.MATCH_PARENT
            )
            for (j in 0 until 10) {
                if (!(i == 2 && (j == 1 || j == 9))) {
                    val button = Button(this)
                    if (i == 2 && j == 0) {
                        button.layoutParams = TableRow.LayoutParams(
                            TableRow.LayoutParams.WRAP_CONTENT,
                            TableRow.LayoutParams.MATCH_PARENT,
                            0.5f
                        )
                        button.contentDescription = "❌"
                        button.text = "❌"
                    }
                    else if (i == 2 && j == 8) {
                        button.layoutParams = TableRow.LayoutParams(
                            TableRow.LayoutParams.WRAP_CONTENT,
                            TableRow.LayoutParams.MATCH_PARENT,
                            0.5f
                        )
                        button.contentDescription = "✔"
                        button.text = "✔"
                    } else {
                        button.layoutParams = TableRow.LayoutParams(
                            TableRow.LayoutParams.WRAP_CONTENT,
                            TableRow.LayoutParams.MATCH_PARENT,
                            1f
                        )
                        button.contentDescription = alphabet[index].toString()
                        button.text = alphabet[index].toString()
                        index++
                    }
                    buttonList.add(button)
                    tableRow.addView(button)
                }
            }
            tableLayout.addView(tableRow)
        }
        return buttonList
    }
}