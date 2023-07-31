package dmitriy.losev.yandex.domain.usecases

import dmitriy.losev.exception.ErrorHandler
import dmitriy.losev.yandex.core.YandexBaseUseCase
import dmitriy.losev.yandex.core.exception.YandexVerifyTokenException
import dmitriy.losev.yandex.domain.repositories.YandexDataRepository


class YandexDataUseCases(
    errorHandler: ErrorHandler,
    private val yandexDataRepository: YandexDataRepository
): YandexBaseUseCase(errorHandler = errorHandler)  {

    suspend fun verifyToken(token: String) = safeCall {
        if (!yandexDataRepository.verifyToken(token = token)) {
            throw YandexVerifyTokenException()
        }
    }

    suspend fun createToken(): String {
        return "eyJhbGciOiJSUzI1NiJ9.eyJhdWQiOiJodHRwczovL2lkZW50aXR5dG9vbGtpdC5nb29nbGVhcGlzLmNvbS9nb29nbGUuaWRlbnRpdHkuaWRlbnRpdHl0b29sa2l0LnYxLklkZW50aXR5VG9vbGtpdCIsImV4cCI6MTY5MDc3MDg1OSwiaWF0IjoxNjkwNzY3MjU5LCJpc3MiOiJmaXJlYmFzZS1hZG1pbnNkay16dGp1eUB0dXRvcnNzY2hlZHVsZS0yMzA1Zi5pYW0uZ3NlcnZpY2VhY2NvdW50LmNvbSIsInN1YiI6ImZpcmViYXNlLWFkbWluc2RrLXp0anV5QHR1dG9yc3NjaGVkdWxlLTIzMDVmLmlhbS5nc2VydmljZWFjY291bnQuY29tIiwidWlkIjoiMTQ1OTQ2MTM0MiJ9.TtFDJrK7zwLxUwepFqakUB70_t0TP6zNhE9pr3WuTywv1z9VBXGQIFvmCTsq2XpkNAQZmiYXFh-yvarZOcTK5pOfs6cWF1fX2AP48nnZ0V0JOFKeX-qfkmkOtHYbNwsLiy31hYW3ljjgckeHlgDKuw35Dgdvu0FQ8bzTCFM86uZhG6sGMzV1qG40nJ7ZxZ2aeWGzlSszsj1GPWNcpUygsBXFzuaWIGVtroAmpdHUB2BZWL-QGkhnNgUEQeV3QW04mH3I3ZY3xXiHq8NeEipn9ihty0cmzELmZ4cZrIWD39XEeNwNk28XZSW9BZnPb2PQ67prSmllBnpYO1pDFRAgWA"//return FirebaseAuth.getInstance().createCustomToken("1459461342")
    }

    suspend fun getUserData(token: String) = safeCall {
        yandexDataRepository.getUserData(token = token)
    }
}