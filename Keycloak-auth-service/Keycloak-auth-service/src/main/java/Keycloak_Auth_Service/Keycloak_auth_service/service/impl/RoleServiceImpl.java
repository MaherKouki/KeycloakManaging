package Keycloak_Auth_Service.Keycloak_auth_service.service.impl;

import Keycloak_Auth_Service.Keycloak_auth_service.service.RoleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
@Slf4j
public class RoleServiceImpl implements RoleService {
    @Override
    public void assignRole(String userId, String roleName) {

    }
}
