package services.impl;

import errors.BusinessError;
import io.vavr.control.Either;
import model.AdminUser;
import model.User;
import repositories.AdminRepository;
import repositories.UserRepository;
import services.AdminService;

import java.time.OffsetDateTime;
import java.util.Optional;

public class AdminServiceWitoutRefactorImpl implements AdminService {

    private UserRepository userRepository;
    private AdminRepository adminRepository;

    public AdminServiceWitoutRefactorImpl(UserRepository userRepository, AdminRepository adminRepository) {
        this.userRepository = userRepository;
        this.adminRepository = adminRepository;
    }

    @Override
    public Either<BusinessError, Boolean> createAdmin(String cod, String codRequester) {
        Either<BusinessError, Boolean> result = null;
        Optional<User> user = userRepository.getUser(cod);
        if (user.isPresent()) {
            if (!user.get().getCode().equalsIgnoreCase(codRequester)) {
                AdminUser userToInsert = new AdminUser(cod, true, codRequester, OffsetDateTime.now());
                Optional<AdminUser> userAdmin = adminRepository.getUserAdmin(userToInsert.getCode());
                if (userAdmin.isPresent()) {
                    result = adminRepository.updateAdmin(userToInsert);
                } else {
                    result =  adminRepository.insertAdmin(userToInsert);
                }
            } else {
                result = Either.left(new BusinessError("Users are the same"));
            }
        } else {
            result = Either.left(new BusinessError("User dont exist"));
        }

        return result;
    }

    @Override
    public Either<BusinessError, Boolean> deleteAdmin(String cod, String codRequester) {
        return null;
    }
}
