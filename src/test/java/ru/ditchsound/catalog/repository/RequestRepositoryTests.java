package ru.ditchsound.catalog.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import ru.ditchsound.catalog.model.Request;
import ru.ditchsound.catalog.util.DataUtils;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@ActiveProfiles("test")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class RequestRepositoryTests {

    @Autowired
    private RequestRepository requestRepository;

    @Autowired
    private TestEntityManager entityManager;

    @BeforeEach
    void setUp() {
        entityManager.getEntityManager()
                .createNativeQuery("TRUNCATE TABLE test_db.public.request RESTART IDENTITY CASCADE")
                .executeUpdate();
    }


    @Test
    @DisplayName("Тест поиска заявки по имени заявки")
    public void givenRequestCreated_whenFindByRequestName_thenRequestIsReturned(){
        //given
        Request requestToSave = DataUtils.getRequestTransient();
        requestRepository.save(requestToSave);
        //when
        Request receivedRequest = requestRepository.findByRequestName(requestToSave.getRequestName())
                .orElse(null);
        //then
        assertThat(receivedRequest).isNotNull();
        assertThat(receivedRequest.getRequestName()).isEqualTo("заявка номер 1");
    }

    @Test
    @DisplayName("Тест поиска заявки по id и статусу заявки")
    public void givenRequestCreated_whenFindByIdAndRequestStatus_thenRequestIsReturned(){
        //given
        Request requestToSave = DataUtils.getRequestTransient();
        requestRepository.save(requestToSave);
        //when
        Request receivedRequest = requestRepository
                .findByIdAndRequestStatus(requestToSave.getId(), requestToSave.getRequestStatus())
                .orElse(null);
        //then
        assertThat(receivedRequest).isNotNull();
        assertThat(receivedRequest.getId()).isEqualTo(1l);

    }


}
