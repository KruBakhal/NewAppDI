package com.example.newappdi.tictacto

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import com.example.newappdi.databinding.ActivityTicTacToactivityBinding

class TicTacTOActivity : AppCompatActivity() {

    var user1 = false
    var user2 = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityTicTacToactivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnSubmit.setOnClickListener {
            val span = binding.edInput.text.toString().toInt()

            val listMatrix = arrayListOf<MatrixModel>()
            //create list of matrix
            var counter = 0;
            for (row in 0..(span - 1)) {
                for (columns in 0..(span - 1)) {
                    val id = "${row}${columns}".toInt()
                    listMatrix.add(MatrixModel(counter, null, row = row, columns = columns))
                    counter++
                }
            }
            val adapter = TicTacAdapter()
            adapter.differ.submitList(listMatrix)
            user1 = true
            adapter.setOnItemClickListner { model ->
                if (!model.user.isNullOrEmpty())
                    return@setOnItemClickListner
                var user = "1"
                if (user1) {
                    user = "1"
                    user1 = false
                    user2 = true
                } else if (user2) {
                    user = "2"
                    user2 = false
                    user1 = true
                }
                val index = listMatrix.filterIndexed { index, matrixModel ->
                    return@filterIndexed matrixModel.id == model.id
                }
                model.user = user
                listMatrix.set(index.last().id, model)
                Log.d("TAG", "onCreate: position: ${model.row}${model.columns} user:$user")
                Log.d("TAG", "onCreate: list: ${listMatrix.toString()}")

                checkWinner(listMatrix, span, model.row, model.columns, user)

                adapter.differ.submitList(listMatrix)
                adapter.notifyDataSetChanged()
            }
            binding.rvTicTac.layoutManager = GridLayoutManager(this, span)
            binding.rvTicTac.adapter = adapter

        }
    }

    fun checkWinner(
        listMatrix: ArrayList<MatrixModel>,
        span: Int,
        row: Int,
        columns: Int,
        user: String
    ) {
        // left to right
        var count = 0;
        listMatrix.forEach {
            if (it.row == row && !it.user.isNullOrEmpty() && it.user.equals(user)) {
                count++
            }
        }
        if (count == span) {
            Toast.makeText(this, "Winner is user:$user", Toast.LENGTH_LONG).show()
        } else {
            // top to botton
            count = 0;
            listMatrix.forEach {
                if (it.columns == columns && !it.user.isNullOrEmpty() && it.user.equals(user)) {
                    count++
                }
            }
            if (count == span) {
                Toast.makeText(this, "top to botton Winner is user:$user", Toast.LENGTH_LONG).show()
            } else {

                count = 0; // diagonal
                listMatrix.forEach {
                    if (it.row == it.columns &&
                        !it.user.isNullOrEmpty() && it.user.equals(
                            user
                        )
                    ) {
//                        Log.d("TAG1", "checkWinner: ${it.row}${it.columns} user:$user")
                        count++
                    } else if (((it.row + it.columns) == (span - 1))
                        && !it.user.isNullOrEmpty() && it.user.equals(
                            user
                        )
                    ) {
//                        Log.d("TAG1", "else : ${it.row}${it.columns} user:$user")
                        count++
                    }
                }
                if (count == span) {
                    Toast.makeText(this, "diagonal Winner is user:$user", Toast.LENGTH_LONG).show()
                }
            }

        }

    }

    /* private fun check_diagonal_bottom_left_top_right(
         position: Int,
         list: java.util.ArrayList<MatrixModel>,
     ): Boolean {
         var active = -1
         var count = -1

         var pos3 = position
         var pos4 = position
         var isFirst3 = true
         var isFirst4 = true
         Log.d("TAG", "check_diagonal: part1")


         for (j in 0 until span) {
             if (!isFirst3) {
                 pos3 = pos3 - ((span - 1))
             } else
                 isFirst3 = false

             if (pos3 >= 0 && pos3 < list.size) {
 //                Log.d("TAG", "user 2 --> : " + pos3)

                 val matrixModel: MatrixModel = list.get(pos3)
                 if (matrixModel.user == 0) {
                     if (active == 1) {
                         break
                     }
                     active = 0;
                     count++
                     Log.d("TAG", "user 1: " + pos3 + " active " + active + "  count: " + count)

                 } else if (matrixModel.user == 1) {
                     if (active == 0) {
                         break
                     }
                     active = 1
                     count++
                     //Log.d("TAG", "user 2: " + pos3 + " active " + active + "  count: " + count)

                 }
             } else {
                 break
             }
         }
         Log.d("TAG", "check_diagonal: part2")
         for (j in 0 until span) {
             if (!isFirst4) {
                 pos4 = pos4 + ((span - 1))
             } else
                 isFirst4 = false

             if (pos4 >= 0 && pos4 < list.size) {
 //                Log.d("TAG", "user 2 --> : " + pos4)

                 val matrixModel: MatrixModel = list.get(pos4)
                 if (matrixModel.user == 0) {
                     if (active == 1) {
                         break
                     }
                     active = 0;
                     count++
                     Log.d("TAG", "user 1: " + pos4 + " active " + active + "  count: " + count)

                 } else if (matrixModel.user == 1) {
                     if (active == 0) {
                         break
                     }
                     active = 1
                     count++
                     //   Log.d("TAG", "user 2: " + pos4 + " active " + active + "  count: " + count)

                 }
             } else {
                 break
             }
         }


         if (count == span) {
             return true
         } else {
             return false
         }
     }

     private fun check_diagonal_top_left_bottom_right(
         position: Int,
         list: java.util.ArrayList<MatrixModel>,
     ): Boolean {
         var active = -1
         var count = -1

         var pos3 = position
         var pos4 = position
         var isFirst3 = true
         var isFirst4 = true
         Log.d("TAG", "check_diagonal: part1")

         for (j in 0 until span) {
             if (!isFirst3) {
                 pos3 = pos3 - (span + 1)
             } else
                 isFirst3 = false

             if (pos3 >= 0 && pos3 < list.size) {
 //                Log.d("TAG", "user 2 --> : " + pos3)

                 val matrixModel: MatrixModel = list.get(pos3)
                 if (matrixModel.user == 0) {
                     if (active == 1) {
                         break
                     }
                     active = 0;
                     count++
                     Log.d("TAG", "user 1: " + pos3 + " active " + active + "  count: " + count)

                 } else if (matrixModel.user == 1) {
                     if (active == 0) {
                         break
                     }
                     active = 1
                     count++
                     //Log.d("TAG", "user 2: " + pos3 + " active " + active + "  count: " + count)

                 }
             } else {
                 break
             }
         }
         Log.d("TAG", "check_diagonal: part2")
         for (j in 0 until span) {
             if (!isFirst4) {
                 pos4 = pos4 + (span + 1)
             } else
                 isFirst4 = false

             if (pos4 >= 0 && pos4 < list.size) {
 //                Log.d("TAG", "user 2 --> : " + pos4)

                 val matrixModel: MatrixModel = list.get(pos4)
                 if (matrixModel.user == 0) {
                     if (active == 1) {
                         break
                     }
                     active = 0;
                     count++
                     Log.d("TAG", "user 1: " + pos4 + " active " + active + "  count: " + count)

                 } else if (matrixModel.user == 1) {
                     if (active == 0) {
                         break
                     }
                     active = 1
                     count++
                     //   Log.d("TAG", "user 2: " + pos4 + " active " + active + "  count: " + count)

                 }
             } else {
                 break
             }
         }


         if (count == span) {
             return true
         } else {
             return false
         }
     }

     private fun check_side_by_side(position: Int, list: java.util.ArrayList<MatrixModel>): Boolean {
         var active = -1
         var count = -1

         var pos3 = position
         var pos4 = position
         var isFirst3 = true
         var isFirst4 = true
         for (j in 0 until span) {
             if (!isFirst3) {
                 pos3 = pos3 - 1
             } else
                 isFirst3 = false

             if (pos3 >= 0 && pos3 < list.size) {
                 Log.d("TAG", "user 2 --> : " + pos3)

                 val matrixModel: MatrixModel = list.get(pos3)
                 if (matrixModel.user == 0) {
                     if (active == 1) {
                         break
                     }
                     active = 0;
                     count++
                     Log.d("TAG", "user 1: " + pos3 + " active " + active + "  count: " + count)

                 } else if (matrixModel.user == 1) {
                     if (active == 0) {
                         break
                     }
                     active = 1
                     count++
                     Log.d("TAG", "user 2: " + pos3 + " active " + active + "  count: " + count)

                 }
             } else {
                 break
             }
         }

         for (j in 0 until span) {
             if (!isFirst4) {
                 pos4 = pos4 + 1
             } else
                 isFirst4 = false

             if (pos4 >= 0 && pos4 < list.size) {
                 Log.d("TAG", "user 2 --> : " + pos4)

                 val matrixModel: MatrixModel = list.get(pos4)
                 if (matrixModel.user == 0) {
                     if (active == 1) {
                         break
                     }
                     active = 0;
                     count++
                     Log.d("TAG", "user 1: " + pos4 + " active " + active + "  count: " + count)

                 } else if (matrixModel.user == 1) {
                     if (active == 0) {
                         break
                     }
                     active = 1
                     count++
                     Log.d("TAG", "user 2: " + pos4 + " active " + active + "  count: " + count)

                 }
             } else {
                 break
             }
         }


         if (count == span) {
             return true
         } else {
             return false
         }
     }

     private fun check_top_bottom_with_column(
         position: Int,
         list: java.util.ArrayList<MatrixModel>,
     ): Boolean {
         var active = -1
         var count = -1

         var pos1 = position
         var pos2 = position
         var pos3 = position
         var pos4 = position
         var isFirst1 = true
         var isFirst2 = true
         var isFirst3 = true
         var isFirst4 = true

         for (j in 0 until span) {
             if (!isFirst1) {
                 pos1 = pos1 - span
             } else
                 isFirst1 = false


             if (pos1 >= 0 && pos1 < list.size) {
                 Log.d("TAG", "user 1: " + pos1)

                 val matrixModel: MatrixModel = list.get(pos1)
                 if (matrixModel.user == 0) {
                     if (active == 1) {
                         break
                     }
                     active = 0;
                     count++
                     Log.d("TAG", "user 1: " + pos1 + " active " + active + "  count: " + count)

                 } else if (matrixModel.user == 1) {
                     if (active == 0) {
                         break
                     }
                     active = 1
                     count++

                     Log.d("TAG", "user 2: " + pos1 + " active " + active + "  count: " + count)
                 }
             } else {
                 break
             }
         }

         for (j in 0 until span) {
             if (!isFirst2) {
                 pos2 = pos2 + span
             } else
                 isFirst2 = false

             if (pos2 >= 0 && pos2 < list.size) {
                 Log.d("TAG", "user 2 --> : " + pos2)

                 val matrixModel: MatrixModel = list.get(pos2)
                 if (matrixModel.user == 0) {
                     if (active == 1) {
                         break
                     }
                     active = 0;
                     count++
                     Log.d("TAG", "user 1: " + pos2 + " active " + active + "  count: " + count)

                 } else if (matrixModel.user == 1) {
                     if (active == 0) {
                         break
                     }
                     active = 1
                     count++
                     Log.d("TAG", "user 2: " + pos2 + " active " + active + "  count: " + count)

                 }
             } else {
                 break
             }
         }

         return count == span
     }

     private fun check_left_to_right(
         position: Int,
         list: java.util.ArrayList<MatrixModel>,
     ): Boolean {
         var a = 0
         var b = 0

         var i1 = list.get(position).row
         var j1 = list.get(position).columns
         var count = -1
         for (i in 0 until list.size) {
             i1 = i1 - 1
             j1 = list.get(position).columns
             val matrixModel: MatrixModel = list.get(position)

             if (i1 >= 0 && j1 >= 0 && i1 < span && j1 < span) {

                 if (matrixModel.user == 0) {
                     if (a == 1) {
                         break
                     }
                     a = 0;
                     count++
                 } else if (matrixModel.user == 1) {
                     if (a == 0) {
                         break
                     }
                     a = 1
                     count++
                 } else {
                     a = -1
                 }
             }
             if (count == span) {
                 return true
             }
             count = 0

             i1 = list.get(position).row
             j1 = list.get(i).columns + 1

             if (i1 >= 0 && j1 >= 0 && i1 < span && j1 < span) {

                 if (matrixModel.user == 0) {
                     if (a == 1) {
                         break
                     }
                     a = 0;

                     count++;
                 } else if (matrixModel.user == 1) {
                     if (a == 0) {
                         break
                     }
                     a = 1
                     count++;
                 } else {
                     a = -1
                 }
             }

         }
         if (count == span) {
             return true
         } else {
             return false
         }
     }


     private fun check_bottom_right_to_top_left(
         position: Int,
         list: java.util.ArrayList<MatrixModel>,
     ): Boolean {
         var a = 0
         var b = 0

         var i1 = list.get(position).row
         var j1 = list.get(position).columns
         var count = -1
         for (i in 0 until list.size) {
             i1 = i1 - 1
             j1 = j1 - 1
             val matrixModel: MatrixModel = list.get(position)

             if (i1 >= 0 && j1 >= 0 && i1 < span && j1 < span) {

                 if (matrixModel.user == 0) {
                     if (a == 1) {
                         break
                     }
                     a = 0;
                     count++
                 } else if (matrixModel.user == 1) {
                     if (a == 0) {
                         break
                     }
                     a = 1
                     count++
                 } else {
                     a = -1
                 }
             }

             i1 = i1 + 1
             j1 = j1 + 1

             if (i1 >= 0 && j1 >= 0 && i1 < span && j1 < span) {

                 if (matrixModel.user == 0) {
                     if (a == 1) {
                         break
                     }
                     a = 0;

                     count++;
                 } else if (matrixModel.user == 1) {
                     if (a == 0) {
                         break
                     }
                     a = 1
                     count++;
                 } else {
                     a = -1
                 }
             }

         }
         if (count == span) {
             return true
         } else {
             return false
         }
     }

     private fun check_bottom_left_to_top_right(
         position: Int,
         list: ArrayList<MatrixModel>,
     ): Boolean {
         var a = 0
         var b = 0
         var i1 = list.get(position).row
         var j1 = list.get(position).columns
         var count = -1
         for (i in list.get(position).row until list.size) {
             for (j in list.get(position).columns until list.size) {
                 i1 = i1 - 1
                 j1 = j1 + 1
                 val matrixModel: MatrixModel = list.get(position)

                 if (i1 >= 0 && j1 >= 0 && i1 < span && j1 < span) {

                     if (matrixModel.user == 0) {
                         if (a == 1) {
                             break
                         }
                         a = 0;
                         count++
                     } else if (matrixModel.user == 1) {
                         if (a == 0) {
                             break
                         }
                         a = 1
                         count++
                     } else {
                         a = -1
                     }
                 }
                 i1 = list.get(position).row
                 j1 = list.get(position).columns

                 i1 = i1 + 1
                 j1 = j1 - 1

                 if (i1 >= 0 && j1 >= 0 && i1 < span && j1 < span) {

                     if (matrixModel.user == 0) {
                         if (a == 1) {
                             break
                         }
                         a = 0;

                         count++;
                     } else if (matrixModel.user == 1) {
                         if (a == 0) {
                             break
                         }
                         a = 1
                         count++;
                     } else {
                         a = -1
                     }
                 }

             }
         }

         if (count == span) {
             return true
         } else {
             return false
         }
     }*/
}