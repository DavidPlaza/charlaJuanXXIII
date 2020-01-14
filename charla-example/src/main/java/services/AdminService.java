package services;

import errors.BusinessError;
import io.vavr.control.Either;

public interface AdminService {

    Either<BusinessError, Boolean> createAdmin(final String cod, final String codRequester);

    Either<BusinessError, Boolean> deleteAdmin(final String cod, final String codRequester);
}
