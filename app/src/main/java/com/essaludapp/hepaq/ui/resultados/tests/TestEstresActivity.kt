package com.essaludapp.hepaq.ui.resultados.tests

import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import com.essaludapp.hepaq.R
import com.essaludapp.hepaq.common.Constants
import com.essaludapp.hepaq.common.SharedPreferencesManager
import com.quickbirdstudios.surveykit.*
import com.quickbirdstudios.surveykit.result.TaskResult
import com.quickbirdstudios.surveykit.steps.CompletionStep
import com.quickbirdstudios.surveykit.steps.InstructionStep
import com.quickbirdstudios.surveykit.steps.QuestionStep
import com.quickbirdstudios.surveykit.survey.SurveyView

class TestEstresActivity : AppCompatActivity() {
    private lateinit var survey: SurveyView
    private lateinit var container: ViewGroup
    private lateinit var resultado: ViewGroup
    private lateinit var toolbar: Toolbar
    private lateinit var tvPuntaje: TextView
    private lateinit var tvMensaje: TextView
    private lateinit var btnRegresar: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test_estres)

        //Ocultar barra de toolbar
        supportActionBar!!.hide()
        toolbar = findViewById(R.id.toolbar)
        toolbar.setNavigationOnClickListener { finish() }

        survey = findViewById(R.id.survey_view)
        container = findViewById(R.id.surveyContainer)
        resultado = findViewById(R.id.resultado)
        tvPuntaje = findViewById(R.id.tvPuntaje)
        tvMensaje = findViewById(R.id.tvMensaje)
        btnRegresar = findViewById(R.id.btnRegresar)

        btnRegresar.setOnClickListener {
            onBackPressed()
        }

        setupSurvey(survey)
    }

    private fun setupSurvey(surveyView: SurveyView?) {
        val steps = listOf(
                InstructionStep(
                        title = this.resources.getString(R.string.estres_title),
                        text = this.resources.getString(R.string.estres_text),
                        buttonText = this.resources.getString(R.string.intro_start)
                ),
                QuestionStep(
                        title = this.resources.getString(R.string.estres_pss1_title),
                        text = this.resources.getString(R.string.estres_pss1),
                        answerFormat = AnswerFormat.SingleChoiceAnswerFormat(
                                textChoices = listOf(
                                        TextChoice(this.resources.getString(R.string.estres_pss_op0)),
                                        TextChoice(this.resources.getString(R.string.estres_pss_op1)),
                                        TextChoice(this.resources.getString(R.string.estres_pss_op2)),
                                        TextChoice(this.resources.getString(R.string.estres_pss_op3)),
                                        TextChoice(this.resources.getString(R.string.estres_pss_op4))
                                )
                        )
                ),
                QuestionStep(
                        title = this.resources.getString(R.string.estres_pss2_title),
                        text = this.resources.getString(R.string.estres_pss2),
                        answerFormat = AnswerFormat.SingleChoiceAnswerFormat(
                                textChoices = listOf(
                                        TextChoice(this.resources.getString(R.string.estres_pss_op0)),
                                        TextChoice(this.resources.getString(R.string.estres_pss_op1)),
                                        TextChoice(this.resources.getString(R.string.estres_pss_op2)),
                                        TextChoice(this.resources.getString(R.string.estres_pss_op3)),
                                        TextChoice(this.resources.getString(R.string.estres_pss_op4))
                                )
                        )
                ),
                QuestionStep(
                        title = this.resources.getString(R.string.estres_pss3_title),
                        text = this.resources.getString(R.string.estres_pss3),
                        answerFormat = AnswerFormat.SingleChoiceAnswerFormat(
                                textChoices = listOf(
                                        TextChoice(this.resources.getString(R.string.estres_pss_op0)),
                                        TextChoice(this.resources.getString(R.string.estres_pss_op1)),
                                        TextChoice(this.resources.getString(R.string.estres_pss_op2)),
                                        TextChoice(this.resources.getString(R.string.estres_pss_op3)),
                                        TextChoice(this.resources.getString(R.string.estres_pss_op4))
                                )
                        )
                ),
                QuestionStep(
                        title = this.resources.getString(R.string.estres_pss4_title),
                        text = this.resources.getString(R.string.estres_pss4),
                        answerFormat = AnswerFormat.SingleChoiceAnswerFormat(
                                textChoices = listOf(
                                        TextChoice(this.resources.getString(R.string.estres_pss_op0)),
                                        TextChoice(this.resources.getString(R.string.estres_pss_op1)),
                                        TextChoice(this.resources.getString(R.string.estres_pss_op2)),
                                        TextChoice(this.resources.getString(R.string.estres_pss_op3)),
                                        TextChoice(this.resources.getString(R.string.estres_pss_op4))
                                )
                        )
                ),
                QuestionStep(
                        title = this.resources.getString(R.string.estres_pss5_title),
                        text = this.resources.getString(R.string.estres_pss5),
                        answerFormat = AnswerFormat.SingleChoiceAnswerFormat(
                                textChoices = listOf(
                                        TextChoice(this.resources.getString(R.string.estres_pss_op0)),
                                        TextChoice(this.resources.getString(R.string.estres_pss_op1)),
                                        TextChoice(this.resources.getString(R.string.estres_pss_op2)),
                                        TextChoice(this.resources.getString(R.string.estres_pss_op3)),
                                        TextChoice(this.resources.getString(R.string.estres_pss_op4))
                                )
                        )
                ),
                QuestionStep(
                        title = this.resources.getString(R.string.estres_pss6_title),
                        text = this.resources.getString(R.string.estres_pss6),
                        answerFormat = AnswerFormat.SingleChoiceAnswerFormat(
                                textChoices = listOf(
                                        TextChoice(this.resources.getString(R.string.estres_pss_op0)),
                                        TextChoice(this.resources.getString(R.string.estres_pss_op1)),
                                        TextChoice(this.resources.getString(R.string.estres_pss_op2)),
                                        TextChoice(this.resources.getString(R.string.estres_pss_op3)),
                                        TextChoice(this.resources.getString(R.string.estres_pss_op4))
                                )
                        )
                ),
                QuestionStep(
                        title = this.resources.getString(R.string.estres_pss7_title),
                        text = this.resources.getString(R.string.estres_pss7),
                        answerFormat = AnswerFormat.SingleChoiceAnswerFormat(
                                textChoices = listOf(
                                        TextChoice(this.resources.getString(R.string.estres_pss_op0)),
                                        TextChoice(this.resources.getString(R.string.estres_pss_op1)),
                                        TextChoice(this.resources.getString(R.string.estres_pss_op2)),
                                        TextChoice(this.resources.getString(R.string.estres_pss_op3)),
                                        TextChoice(this.resources.getString(R.string.estres_pss_op4))
                                )
                        )
                ),
                QuestionStep(
                        title = this.resources.getString(R.string.estres_pss8_title),
                        text = this.resources.getString(R.string.estres_pss8),
                        answerFormat = AnswerFormat.SingleChoiceAnswerFormat(
                                textChoices = listOf(
                                        TextChoice(this.resources.getString(R.string.estres_pss_op0)),
                                        TextChoice(this.resources.getString(R.string.estres_pss_op1)),
                                        TextChoice(this.resources.getString(R.string.estres_pss_op2)),
                                        TextChoice(this.resources.getString(R.string.estres_pss_op3)),
                                        TextChoice(this.resources.getString(R.string.estres_pss_op4))
                                )
                        )
                ),
                QuestionStep(
                        title = this.resources.getString(R.string.estres_pss9_title),
                        text = this.resources.getString(R.string.estres_pss9),
                        answerFormat = AnswerFormat.SingleChoiceAnswerFormat(
                                textChoices = listOf(
                                        TextChoice(this.resources.getString(R.string.estres_pss_op0)),
                                        TextChoice(this.resources.getString(R.string.estres_pss_op1)),
                                        TextChoice(this.resources.getString(R.string.estres_pss_op2)),
                                        TextChoice(this.resources.getString(R.string.estres_pss_op3)),
                                        TextChoice(this.resources.getString(R.string.estres_pss_op4))
                                )
                        )
                ),
                QuestionStep(
                        title = this.resources.getString(R.string.estres_pss10_title),
                        text = this.resources.getString(R.string.estres_pss10),
                        answerFormat = AnswerFormat.SingleChoiceAnswerFormat(
                                textChoices = listOf(
                                        TextChoice(this.resources.getString(R.string.estres_pss_op0)),
                                        TextChoice(this.resources.getString(R.string.estres_pss_op1)),
                                        TextChoice(this.resources.getString(R.string.estres_pss_op2)),
                                        TextChoice(this.resources.getString(R.string.estres_pss_op3)),
                                        TextChoice(this.resources.getString(R.string.estres_pss_op4))
                                )
                        )
                ),
                QuestionStep(
                        title = this.resources.getString(R.string.estres_pss11_title),
                        text = this.resources.getString(R.string.estres_pss11),
                        answerFormat = AnswerFormat.SingleChoiceAnswerFormat(
                                textChoices = listOf(
                                        TextChoice(this.resources.getString(R.string.estres_pss_op0)),
                                        TextChoice(this.resources.getString(R.string.estres_pss_op1)),
                                        TextChoice(this.resources.getString(R.string.estres_pss_op2)),
                                        TextChoice(this.resources.getString(R.string.estres_pss_op3)),
                                        TextChoice(this.resources.getString(R.string.estres_pss_op4))
                                )
                        )
                ),
                QuestionStep(
                        title = this.resources.getString(R.string.estres_pss12_title),
                        text = this.resources.getString(R.string.estres_pss12),
                        answerFormat = AnswerFormat.SingleChoiceAnswerFormat(
                                textChoices = listOf(
                                        TextChoice(this.resources.getString(R.string.estres_pss_op0)),
                                        TextChoice(this.resources.getString(R.string.estres_pss_op1)),
                                        TextChoice(this.resources.getString(R.string.estres_pss_op2)),
                                        TextChoice(this.resources.getString(R.string.estres_pss_op3)),
                                        TextChoice(this.resources.getString(R.string.estres_pss_op4))
                                )
                        )
                ),
                QuestionStep(
                        title = this.resources.getString(R.string.estres_pss13_title),
                        text = this.resources.getString(R.string.estres_pss13),
                        answerFormat = AnswerFormat.SingleChoiceAnswerFormat(
                                textChoices = listOf(
                                        TextChoice(this.resources.getString(R.string.estres_pss_op0)),
                                        TextChoice(this.resources.getString(R.string.estres_pss_op1)),
                                        TextChoice(this.resources.getString(R.string.estres_pss_op2)),
                                        TextChoice(this.resources.getString(R.string.estres_pss_op3)),
                                        TextChoice(this.resources.getString(R.string.estres_pss_op4))
                                )
                        )
                ),
                QuestionStep(
                        title = this.resources.getString(R.string.estres_pss14_title),
                        text = this.resources.getString(R.string.estres_pss14),
                        answerFormat = AnswerFormat.SingleChoiceAnswerFormat(
                                textChoices = listOf(
                                        TextChoice(this.resources.getString(R.string.estres_pss_op0)),
                                        TextChoice(this.resources.getString(R.string.estres_pss_op1)),
                                        TextChoice(this.resources.getString(R.string.estres_pss_op2)),
                                        TextChoice(this.resources.getString(R.string.estres_pss_op3)),
                                        TextChoice(this.resources.getString(R.string.estres_pss_op4))
                                )
                        )
                ),

                CompletionStep(
                        title = this.resources.getString(R.string.finish_question_title),
                        text = this.resources.getString(R.string.finish_question_text),
                        buttonText = this.resources.getString(R.string.finish_question_submit)
                )
        )

        val task = OrderedTask(steps = steps)

        var posisicion = 0;
        var suma = 0;
        surveyView!!.onSurveyFinish = { taskResult: TaskResult, reason: FinishReason ->
            if (reason == FinishReason.Completed) {
                taskResult.results.forEach { stepResult ->
                    //Log.e("ASDF", "answer ${stepResult.results.firstOrNull()!!.stringIdentifier}")
                    suma += calcularResultado(posisicion, stepResult.results.firstOrNull()!!.stringIdentifier)
                    posisicion++;
                }

                //Almacena preferences del login
                SharedPreferencesManager.setStringValue(Constants.TEST_ESTRES_SCORE, suma.toString())
                container.removeAllViews()
                container.visibility = View.INVISIBLE

                tvPuntaje.text = suma.toString()
                tvMensaje.text = getMensajeSegun(suma)

                resultado.visibility = View.VISIBLE
            }
        }

        val configuration = SurveyTheme(
                themeColorDark = ContextCompat.getColor(this, R.color.soft_blue),
                themeColor = ContextCompat.getColor(this, R.color.cyan_normal),
                textColor = ContextCompat.getColor(this, R.color.cyan_text),
                /*abortDialogConfiguration = AbortDialogConfiguration(
                        title = R.string.title,
                        message = R.string.message,
                        neutralMessage = R.string.no,
                        negativeMessage = R.string.yes
                )*/
        )


        surveyView.start(task, configuration)
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        return if (keyCode == KeyEvent.KEYCODE_BACK) {
            survey.backPressed()
            true
        } else false
    }

    private fun calcularResultado(pregunta: Int, res: String): Int {
        return when (pregunta) {
            1, 2, 3, 8, 11, 12, 14 -> if (res.equals(getString(R.string.estres_pss_op0), ignoreCase = true)) 0 else if (res.equals(getString(R.string.estres_pss_op1), ignoreCase = true)) 1 else if (res.equals(getString(R.string.estres_pss_op2), ignoreCase = true)) 2 else if (res.equals(getString(R.string.estres_pss_op3), ignoreCase = true)) 3 else 4
            4, 5, 6, 7, 9, 10, 13 -> if (res.equals(getString(R.string.estres_pss_op0), ignoreCase = true)) 4 else if (res.equals(getString(R.string.estres_pss_op1), ignoreCase = true)) 3 else if (res.equals(getString(R.string.estres_pss_op2), ignoreCase = true)) 2 else if (res.equals(getString(R.string.estres_pss_op3), ignoreCase = true)) 1 else 0
            else -> 0
        }
    }

    private fun getMensajeSegun(suma: Int): String? {
        return if (suma >= 46) "Existe un alto nivel de estrés percibido"
        else if (suma >= 25 && suma <= 45) "Nivel de estrés moderado"
        else "Nivel de estrés bajo"
    }
}