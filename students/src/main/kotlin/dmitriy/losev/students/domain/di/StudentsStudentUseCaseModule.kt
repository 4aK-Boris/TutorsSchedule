package dmitriy.losev.students.domain.di

import dmitriy.losev.students.domain.usecases.student.AddStudentUseCase
import dmitriy.losev.students.domain.usecases.student.ChangeStudentTypeUseCase
import dmitriy.losev.students.domain.usecases.student.CheckStudentUseCase
import dmitriy.losev.students.domain.usecases.student.ConvertStudentToActiveStudentUseCase
import dmitriy.losev.students.domain.usecases.student.ConvertStudentToArchiveStudentUseCase
import dmitriy.losev.students.domain.usecases.student.DeleteStudentUseCase
import dmitriy.losev.students.domain.usecases.student.GetFilterStudentsUseCase
import dmitriy.losev.students.domain.usecases.student.GetStudentUseCase
import dmitriy.losev.students.domain.usecases.student.GetStudentsUseCase
import dmitriy.losev.students.domain.usecases.student.StudentBringBackFromArchiveUseCase
import dmitriy.losev.students.domain.usecases.student.StudentMoveToArchiveUseCase
import dmitriy.losev.students.domain.usecases.student.UpdateStudentUseCase
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val studentsGetStudentUseCaseModule = module {

    factoryOf(::GetStudentUseCase)

    factoryOf(::AddStudentUseCase)

    factoryOf(::UpdateStudentUseCase)

    factoryOf(::DeleteStudentUseCase)

    factoryOf(::GetStudentsUseCase)

    factoryOf(::ChangeStudentTypeUseCase)

    factoryOf(::StudentMoveToArchiveUseCase)

    factoryOf(::StudentBringBackFromArchiveUseCase)

    factoryOf(::ConvertStudentToActiveStudentUseCase)

    factoryOf(::ConvertStudentToArchiveStudentUseCase)

    factoryOf(::CheckStudentUseCase)

    factoryOf(::GetFilterStudentsUseCase)
}