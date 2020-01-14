package services.impl;

import errors.BusinessError;
import io.vavr.control.Either;
import model.AdminUser;
import model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import repositories.AdminRepository;
import repositories.UserRepository;
import services.AdminService;

import java.time.OffsetDateTime;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

class AdminServiceImplTest {

    private AdminRepository adminRepository;
    private UserRepository userRepository;
    private AdminService sut;

    @BeforeEach
    public void setUp() {
        adminRepository = mock(AdminRepository.class);
        userRepository = mock(UserRepository.class);
        sut = new AdminServiceImpl(userRepository, adminRepository);
    }

    @Test
    public void whenAdminNotExistThenShouldCreate() {
        //Given
        String cod = "Test";
        String codRequester = "David";
        User user = new User(cod, "hola");
        Either<BusinessError, Boolean> expected = Either.right(true);

        when(userRepository.getUser(cod)).thenReturn(Optional.of(user));
        when(adminRepository.getUserAdmin(cod)).thenReturn(Optional.empty());
        when(adminRepository.insertAdmin(any())).thenReturn(expected);

        //when
        Either<BusinessError, Boolean> result = sut.createAdmin(cod, codRequester);

        //Then
        assertThat(result, is(notNullValue()));
        verify(adminRepository, times(0)).updateAdmin(any());
        verify(adminRepository, times(1)).insertAdmin(any());
        assertTrue(result.isRight());
    }

    @Test
    public void whenAdminNotExistThenShouldUpdate() {
        //Given
        String cod = "Test";
        String codRequester = "David";
        User user = new User(cod, "hola");
        AdminUser adminUser = new AdminUser(cod, false, codRequester, OffsetDateTime.now());
        Either<BusinessError, Boolean> expected = Either.right(true);

        when(userRepository.getUser(cod)).thenReturn(Optional.of(user));
        when(adminRepository.getUserAdmin(cod)).thenReturn(Optional.of(adminUser));
        when(adminRepository.updateAdmin(any())).thenReturn(expected);

        //when
        Either<BusinessError, Boolean> result = sut.createAdmin(cod, codRequester);

        //Then
        assertThat(result, is(notNullValue()));
        verify(adminRepository, times(1)).updateAdmin(any());
        verify(adminRepository, times(0)).insertAdmin(any());
        assertTrue(result.isRight());
    }

    @Test
    public void whenUserNotExistThenReturnError() {
        //Given
        String cod = "Test";
        String codRequester = "David";

        when(userRepository.getUser(cod)).thenReturn(Optional.empty());

        //when
        Either<BusinessError, Boolean> result = sut.createAdmin(cod, codRequester);

        //Then
        assertThat(result, is(notNullValue()));
        verify(adminRepository, times(0)).updateAdmin(any());
        verify(adminRepository, times(0)).insertAdmin(any());
        assertTrue(result.isLeft());
    }

    @Test
    public void whenUsersAreSameThenReturnError() {
        //Given
        String cod = "David";
        String codRequester = "David";
        User user = new User(cod, "David");

        when(userRepository.getUser(cod)).thenReturn(Optional.of(user));

        //when
        Either<BusinessError, Boolean> result = sut.createAdmin(cod, codRequester);

        //Then
        assertThat(result, is(notNullValue()));
        verify(adminRepository, times(0)).updateAdmin(any());
        verify(adminRepository, times(0)).insertAdmin(any());
        assertTrue(result.isLeft());
    }

    @Test
    public void whenAdminRepositoryErrorShouldReturnError() {
        //Given
        String cod = "Test";
        String codRequester = "David";
        User user = new User(cod, "hola");
        Either<BusinessError, Boolean> expected = Either.left(new BusinessError("test"));

        when(userRepository.getUser(cod)).thenReturn(Optional.of(user));
        when(adminRepository.getUserAdmin(cod)).thenReturn(Optional.empty());
        when(adminRepository.insertAdmin(any())).thenReturn(expected);

        //when
        Either<BusinessError, Boolean> result = sut.createAdmin(cod, codRequester);

        //Then
        assertThat(result, is(notNullValue()));
        verify(adminRepository, times(0)).updateAdmin(any());
        verify(adminRepository, times(1)).insertAdmin(any());
        assertTrue(result.isLeft());
    }
}