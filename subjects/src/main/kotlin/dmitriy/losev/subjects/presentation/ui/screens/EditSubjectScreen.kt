package dmitriy.losev.subjects.presentation.ui.screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import dmitriy.losev.subjects.R
import dmitriy.losev.subjects.core.SubjectsNavigationListener
import dmitriy.losev.subjects.presentation.viewmodels.EditSubjectScreenViewModel
import dmitriy.losev.ui.views.TitleWithSaveIcon
import dmitriy.losev.ui.views.VerticalSpacer
import dmitriy.losev.ui.views.boxes.ColumnSecondaryWithLoader
import dmitriy.losev.ui.views.textfields.DefaultTextField
import org.koin.androidx.compose.koinViewModel

@Composable
fun EditSubjectScreen(
    subjectsNavigationListener: SubjectsNavigationListener,
    subjectId: String,
    viewModel: EditSubjectScreenViewModel = koinViewModel()
) {

    val name by viewModel.name.collectAsState()
    val price by viewModel.price.collectAsState()

    val nameTextFieldState by viewModel.nameTextFieldState.collectAsState()

    LaunchedEffect(key1 = Unit) {
        viewModel.loadSubject(subjectId)
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TitleWithSaveIcon(
                title = stringResource(id = R.string.edit_subject_screen_title),
                back = { viewModel.back(subjectsNavigationListener) },
                save = { viewModel.saveChanges(subjectsNavigationListener, subjectId) }
            )
        }
    ) { paddingValues ->
        ColumnSecondaryWithLoader(viewModel = viewModel, modifier = Modifier.padding(paddingValues = paddingValues)) {


            VerticalSpacer(height = 24.dp)

            DefaultTextField(
                title = stringResource(id = R.string.name),
                hint = stringResource(id = R.string.name),
                text = name,
                onTextChanged = viewModel::onNameChanged,
                imeAction = ImeAction.Next,
                capitalization = KeyboardCapitalization.Words,
                autoCorrect = true,
                textFieldState = nameTextFieldState,
                clearTextFieldState = viewModel::clearTextFieldState
            )

            VerticalSpacer(height = 24.dp)

            DefaultTextField(
                title = stringResource(id = R.string.price),
                hint = stringResource(id = R.string.price),
                text = price,
                onTextChanged = viewModel::onPriceChanged,
                keyboardType = KeyboardType.Number
            )

            VerticalSpacer(height = 24.dp)
        }
    }

}