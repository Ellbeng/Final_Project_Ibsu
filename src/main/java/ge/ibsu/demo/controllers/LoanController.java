package ge.ibsu.demo.controllers;


import ge.ibsu.demo.dto.EditLoan;
import ge.ibsu.demo.dto.SearchLoan;
import ge.ibsu.demo.dto.request.RequestData;
import ge.ibsu.demo.entities.Loan;
import ge.ibsu.demo.entities.enums.Role;
import ge.ibsu.demo.services.LoanService;
import ge.ibsu.demo.utils.GeneralUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Slice;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("api/loan")
public class LoanController {

    @Autowired
    private LoanService loanService;

    @PreAuthorize("hasAuthority('loan:read')")
    @RequestMapping(value = "/all", method = RequestMethod.GET, produces = {"application/json"})
    public List<Loan> getAll(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUserId = authentication.getName();


        if (!authentication.getAuthorities().contains(Role.ADMIN)) {
            throw new AccessDeniedException("You are not authorized to access this resource");
        }
        return loanService.getAllLoan();
    }

    @PreAuthorize("hasAuthority('loan:read')")
    @RequestMapping(value = "/search", method = RequestMethod.POST, produces = {"application/json"})
    public Slice<Loan> search(@RequestBody RequestData<SearchLoan> rd) {
        return loanService.searchByUserId(rd.getData(), rd.getPaging());
    }

    @PreAuthorize("hasAuthority('loan:read')")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = {"application/json"})
    public Loan getById(@PathVariable Long id) throws Exception {

        return loanService.getLoanById(id);
    }

    @PreAuthorize("hasAuthority('loan:read')")
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT, produces = {"application/json"})
    public Loan edit(@PathVariable Long id, @RequestBody EditLoan editLoan) throws Exception {
        GeneralUtil.checkRequiredProperties(editLoan, Arrays.asList("email"));
        return loanService.edit(id, editLoan);
    }
}
