package com.atrezzo.manager.domain.repository;

import com.atrezzo.manager.application.dto.WorkerDTO;
import com.atrezzo.manager.domain.model.EventEntity;
import com.atrezzo.manager.domain.model.ServiceEntity;
import com.atrezzo.manager.domain.model.ServiceWorkerPriceEntity;
import com.atrezzo.manager.domain.model.WorkerEntity;
import com.atrezzo.manager.domain.model.enums.ServiceCategory;
import com.atrezzo.manager.domain.model.enums.ServiceType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ServiceRepository extends JpaRepository<ServiceEntity, Long> {

    @Query("SELECT e FROM EventEntity e JOIN e.eventSessions es JOIN es.sessionServices ss WHERE ss.service.id = :serviceId")
    List<EventEntity> findEventsByService(@Param("serviceId") Long serviceId);

    List<ServiceEntity> findByCategory(ServiceCategory category);

    @Query("select ws.worker from WorkerServiceEntity ws where ws.service.id = :serviceId")
    List<WorkerEntity> findWorkersByServiceId(@Param("serviceId") Long serviceId);

    @Query("SELECT s FROM ServiceEntity s WHERE s.category = :category AND s.type = :type")
    List<ServiceEntity> findServicesByCategoryAndType(@Param("category") ServiceCategory category, @Param("type") ServiceType type);

    @Query("SELECT s FROM ServiceEntity s WHERE s.type = :type")
    List<ServiceEntity> findServicesByType(@Param("type") ServiceType type);


   /* @Query("SELECT swp FROM ServiceWorkerPriceEntity swp WHERE swp.service.id = :serviceId")
    List<ServiceWorkerPriceEntity> getServicesWorkerPriceByService(@Param("serviceId") Long serviceId);
*/

}
