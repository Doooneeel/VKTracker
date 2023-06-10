package ru.vktracker.feature.account.users.domain

/**
 * @author Danil Glazkov on 10.06.2023, 02:43
 */
interface AccountUsersInteractor : ChangeTrackingStatus {

    suspend fun users(): AccountUsersDomainResponse

    class Base(
        private val repository: AccountUsersRepository
    ) : AccountUsersInteractor,
        ChangeTrackingStatus by repository
    {
        override suspend fun users(): AccountUsersDomainResponse = try {
            AccountUsersDomainResponse.Success(repository.accountUsers())
        } catch (exception: Exception) {
            AccountUsersDomainResponse.Failure(exception)
        }
    }

}