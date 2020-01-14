package repositories;

import errors.BusinessError;
import io.vavr.control.Either;
import model.AdminUser;

import java.util.Optional;

public interface AdminRepository {

    Optional<AdminUser> getUserAdmin(String code);

    Either<BusinessError, Boolean> updateAdmin(AdminUser adminUser);

    Either<BusinessError, Boolean> insertAdmin(AdminUser adminUser);

    Either<BusinessError, Boolean> deleteAdmin(AdminUser adminUser);
}
