
import android.app.DatePickerDialog
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.widget.DatePicker
import androidx.fragment.app.DialogFragment
import java.time.Month
import java.util.*

class DatePickerFragment(val listener:(day:Int,month:Int,year:Int)->Unit):DialogFragment(),
    DatePickerDialog.OnDateSetListener {
    //code-->Override Methods--> buscar OncreateDialog
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        //sacar la fecha de hoy
        val c=Calendar.getInstance()
        val day=c.get(Calendar.DAY_OF_MONTH)
        val month=c.get(Calendar.MONTH)
        val year=c.get(Calendar.YEAR)

        //Convertir la activity en un contexto
        val picker=DatePickerDialog(activity as Context,R.style.datePickerTheme,this,year,month,day)
        return picker

    }
    //click derecho en el error
    //un metodo que sobrescribe al original, retorna el dia, año y mes
    override fun onDateSet(view: DatePicker?, year: Int,month:Int, dayofMonth: Int) {
        listener(dayofMonth,month,year)
    }


}
