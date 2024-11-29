package com.amdocs.coe_dashboard.repositories;

import com.amdocs.coe_dashboard.config.CouchbaseConfig;
import com.amdocs.coe_dashboard.models.*;
import com.couchbase.client.java.Bucket;
import com.couchbase.client.java.Cluster;
import com.couchbase.client.java.Collection;
import com.couchbase.client.java.json.JsonArray;
import com.couchbase.client.java.query.QueryOptions;
import com.couchbase.client.java.query.QueryScanConsistency;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class AdminRepositoryImpl implements AdminRepository {
    private final Cluster cluster;
//    private final Bucket bucket;
    private final Collection employeeCol;
    private final Collection requestsCol;
    private final CouchbaseConfig couchbaseConfig;


    public AdminRepositoryImpl(Cluster cluster, Bucket bucket, CouchbaseConfig couchbaseConfig) {
        this.cluster = cluster;
//        this.bucket = bucket;
        this.employeeCol = bucket.scope("dashboard").collection("employee");
        this.requestsCol = bucket.scope("dashboard").collection("register_requests");
        this.couchbaseConfig = couchbaseConfig;
    }
    @Override
    public Optional<Admin> adminLogin(String email, String passwd) {
        String statement = "SELECT * FROM `"
                + couchbaseConfig.getBucketName() + "`.`dashboard`.`admin` WHERE adminEmail = $1 AND adminPassword = $2";
        List<Admin> result = new ArrayList<>();
        cluster
                .query(statement,
                        QueryOptions.queryOptions().parameters(JsonArray.from(email, passwd))
                                .scanConsistency(QueryScanConsistency.REQUEST_PLUS))
                .rowsAs(AdminWrapper.class).forEach(e->result.add(e.getAdmin()));
//        System.out.println(result.rowsAs(AdminWrapper.class));
        return result.isEmpty() ? Optional.empty() : Optional.of(result.get(0));

    }

    @Override
    public List<Employee> findByIdOrName(String input) {
        String statement = "SELECT * FROM `"
                + couchbaseConfig.getBucketName() + "`.`dashboard`.`employee` "
                + "WHERE LOWER(empId) LIKE '%' || LOWER($1) || '%' "
                + "OR LOWER(empEmail) LIKE '%' || LOWER($1) || '%' "
                + "OR LOWER(empName) LIKE '%' || LOWER($1) || '%'";

        List<Employee> result = new ArrayList<>();
        cluster
                .query(statement,
                        QueryOptions.queryOptions().parameters(JsonArray.from(input, input))
                                .scanConsistency(QueryScanConsistency.REQUEST_PLUS))
                .rowsAs(EmployeeWrapper.class).forEach(e -> result.add(e.getEmployee()));
        return result;
    }


    @Override
    public Employee addEmp(String id, Employee employee) {
        employeeCol.insert(id, employee);
        return employee;
    }

    @Override
    public Employee update(String id, Employee employee) {
        employeeCol.replace(id, employee);
        return employee;
    }

    @Override
    public Employee getEmpById(String id) {
        return employeeCol.get(id).contentAs(Employee.class);
    }

    @Override
    public List<Employee> getAllEmp() {
        String statement = "SELECT * FROM `"
                + couchbaseConfig.getBucketName() + "`.`dashboard`.`employee` ";

        List<Employee> result = new ArrayList<>();
        cluster
                .query(statement,
                        QueryOptions.queryOptions()
                                .scanConsistency(QueryScanConsistency.REQUEST_PLUS))
                .rowsAs(EmployeeWrapper.class).forEach(e->result.add(e.getEmployee()));
        return result;
    }

    @Override
    public String delete(String id) {
        employeeCol.remove(id);
        return id;
    }

    @Override
    public List<Employee> getRegisterRequests() {
        String statement = "SELECT * FROM `"
                + couchbaseConfig.getBucketName() + "`.`dashboard`.`register_requests` ";

        List<Employee> result = new ArrayList<>();
        cluster
                .query(statement,
                        QueryOptions.queryOptions()
                                .scanConsistency(QueryScanConsistency.REQUEST_PLUS))
                .rowsAs(RegisterRequestWrapper.class).forEach(e->result.add(e.getRegister_requests()));
        return result;
    }

    @Override
    public Employee approveRequest(Employee employee) {

        employeeCol.insert(employee.getEmpId(), employee);

        employee.setApproved(true);

        requestsCol.remove(employee.getEmpId());

        return employee;
    }

}
