package services.impl;

import errors.BusinessError;
import io.vavr.control.Either;
import model.AdminUser;
import model.User;
import repositories.AdminRepository;
import repositories.UserRepository;
import services.AdminService;

import java.time.OffsetDateTime;

public class AdminServiceImpl implements AdminService {

    private UserRepository userRepository;
    private AdminRepository adminRepository;

    public AdminServiceImpl(UserRepository userRepository, AdminRepository adminRepository) {
        this.userRepository = userRepository;
        this.adminRepository = adminRepository;
    }

    @Override
    public Either<BusinessError, Boolean> createAdmin(String cod, String codRequester) {
        return this.checkUserExist(cod)
                .flatMap(__ -> checkDifferentUsers(cod, codRequester))
                .map(__ -> createAdminUserObject(cod, codRequester, true))
                .flatMap(this::insertAdmin);
    }

    @Override
    public Either<BusinessError, Boolean> deleteAdmin(String cod, String codRequester) {
        return this.checkUserExist(cod)
                .flatMap(__ -> checkDifferentUsers(cod, codRequester))
                .map(__ -> createAdminUserObject(cod, codRequester, true))
                .flatMap(adminRepository::deleteAdmin);
    }

    private Either<BusinessError, User> checkUserExist(String cod) {
        return userRepository.getUser(cod)
                .map(Either::<BusinessError, User>right)
                .orElse(Either.left(new BusinessError("User dont exits")));
    }

    private Either<BusinessError, Boolean> checkDifferentUsers(String cod, String codRequester) {
        return !cod.equalsIgnoreCase(codRequester)
                ? Either.right(true)
                : Either.left(new BusinessError("Users are the same"));
    }

    private AdminUser createAdminUserObject(String cod, String codRequester, Boolean isAdmin) {
        return new AdminUser(cod, isAdmin, codRequester, OffsetDateTime.now());
    }

    private Either<BusinessError, Boolean> insertAdmin(AdminUser adminUser) {
        return adminRepository.getUserAdmin(adminUser.getCode())
                .map(__ -> adminRepository.updateAdmin(adminUser))
                .orElseGet(() -> adminRepository.insertAdmin(adminUser));
    }
}
