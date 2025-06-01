package dijam.c_box_be.databasecheck; // 적절한 패키지 경로로 변경

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class DatabaseHealthService {

    private final JdbcTemplate jdbcTemplate;

    /**
     * -- GETTER --
     *  데이터베이스 URL 반환
     *
     * @return 데이터베이스 URL (application.yml/properties에서 가져옴)
     */ // 프로퍼티 파일에서 읽어온 URL 반환
    @Getter
    @Value("${spring.datasource.url}") // Spring의 프로퍼티 주입
    private String databaseUrl;

    public DatabaseHealthService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public boolean isDatabaseConnected() {
        try {
            jdbcTemplate.execute("SELECT 1"); // 간단한 연결 확인 쿼리
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
