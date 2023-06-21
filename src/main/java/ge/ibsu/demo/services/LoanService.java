package ge.ibsu.demo.services;

import ge.ibsu.demo.dto.AddLoan;
import ge.ibsu.demo.dto.EditLoan;
import ge.ibsu.demo.dto.SearchLoan;
import ge.ibsu.demo.dto.request.Paging;
import ge.ibsu.demo.entities.Loan;
import ge.ibsu.demo.entities.User;
import ge.ibsu.demo.repositories.LoanRepository;
import ge.ibsu.demo.utils.GeneralUtil;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoanService {
    @Autowired
    private LoanRepository loanRepository;

    @Autowired
    private UserService userService;

    public List<Loan> getAllLoan() {
        return loanRepository.findAll();
    }

    public Loan getLoanById(Long id) throws Exception {
        return loanRepository.findById(id).orElseThrow(() ->  new ResourceNotFoundException("RECORD_NOT_FOUND"));
    }

    @Transactional
    public Loan add(AddLoan addLoan) throws Exception {
        Loan loan = new Loan();
        GeneralUtil.getCopyOf(addLoan, loan);
        User user = userService.getById(addLoan.getUserId());
        loan.setUser(user);
        return loanRepository.save(loan);
    }

    @Transactional
    public Loan edit(Long id, EditLoan editLoan) throws Exception {
        Loan loan = getLoanById(id);
        GeneralUtil.getCopyOf(editLoan, loan);
        if (editLoan.getUserId() != null && !editLoan.getUserId().equals(loan.getUser().getId())) {
            User user = userService.getById(editLoan.getUserId());
            loan.setUser(user);
        }
        return loanRepository.save(loan);
    }

    public Slice<Loan> searchByUserId(SearchLoan searchLoan, Paging paging) {
        Long userId=null;
        if (searchLoan.getUserId() != null && !searchLoan.getUserId().equals("")) {
            userId = searchLoan.getUserId() ;
        }
        Pageable pageable = PageRequest.of(paging.getPage(), paging.getSize());
        return loanRepository.searchByUserId(searchLoan.getUserId(),pageable);
    }

}
