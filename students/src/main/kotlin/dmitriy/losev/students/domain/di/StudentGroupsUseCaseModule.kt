package dmitriy.losev.students.domain.di

import dmitriy.losev.students.domain.usecases.groups.AddGroupDataUseCase
import dmitriy.losev.students.domain.usecases.groups.AddGroupStudentsUseCase
import dmitriy.losev.students.domain.usecases.groups.AddGroupUseCase
import dmitriy.losev.students.domain.usecases.groups.ChangeGroupTypeUseCase
import dmitriy.losev.students.domain.usecases.groups.CheckGroupUseCase
import dmitriy.losev.students.domain.usecases.groups.DeleteGroupUseCase
import dmitriy.losev.students.domain.usecases.groups.GetFilterGroupsUseCase
import dmitriy.losev.students.domain.usecases.groups.GetGroupStudentIdsUseCase
import dmitriy.losev.students.domain.usecases.groups.GetGroupStudentNamesUseCase
import dmitriy.losev.students.domain.usecases.groups.GetGroupUseCase
import dmitriy.losev.students.domain.usecases.groups.GetGroupsUseCase
import dmitriy.losev.students.domain.usecases.groups.GroupBringBackFromArchiveUseCase
import dmitriy.losev.students.domain.usecases.groups.GroupMoveToArchiveUseCase
import dmitriy.losev.students.domain.usecases.groups.UpdateGroupUseCase
import dmitriy.losev.students.domain.usecases.student.GetStudentGroupsUseCase
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val studentGroupsUseCaseModule = module {

    factoryOf(::GetStudentGroupsUseCase)

    factoryOf(::UpdateGroupUseCase)

    factoryOf(::CheckGroupUseCase)

    factoryOf(::AddGroupDataUseCase)

    factoryOf(::AddGroupStudentsUseCase)

    factoryOf(::AddGroupUseCase)

    factoryOf(::GetGroupsUseCase)

    factoryOf(::GetFilterGroupsUseCase)

    factoryOf(::GetGroupStudentIdsUseCase)

    factoryOf(::ChangeGroupTypeUseCase)

    factoryOf(::GroupBringBackFromArchiveUseCase)

    factoryOf(::GroupMoveToArchiveUseCase)

    factoryOf(::DeleteGroupUseCase)

    factoryOf(::GetGroupStudentNamesUseCase)

    factoryOf(::GetGroupUseCase)
}