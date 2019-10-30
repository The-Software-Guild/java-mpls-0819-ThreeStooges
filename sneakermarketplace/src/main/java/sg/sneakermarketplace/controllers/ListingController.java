/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.sneakermarketplace.controllers;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.security.Principal;
import java.time.LocalDate;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;
import sg.sneakermarketplace.models.Bid;
import sg.sneakermarketplace.models.Listing;
import sg.sneakermarketplace.models.Purchase;
import sg.sneakermarketplace.models.ShoeCondition;
import sg.sneakermarketplace.models.ShoeModel;
import sg.sneakermarketplace.models.SiteUser;
import sg.sneakermarketplace.models.Size;
import sg.sneakermarketplace.models.Status;
import sg.sneakermarketplace.models.Type;
import sg.sneakermarketplace.services.BidService;
import sg.sneakermarketplace.services.InsufficientFundsServiceException;
import sg.sneakermarketplace.services.InvalidBidException;
import sg.sneakermarketplace.services.ListingService;
import sg.sneakermarketplace.services.PurchaseService;
import sg.sneakermarketplace.services.ServiceInvalidPurchaseException;
import sg.sneakermarketplace.services.ShoeConditionService;
import sg.sneakermarketplace.services.ShoeModelService;
import sg.sneakermarketplace.services.SizeService;
import sg.sneakermarketplace.services.StatusService;
import sg.sneakermarketplace.services.TypeService;
import sg.sneakermarketplace.services.UserDetailsServiceImpl;

/**
 *
 * @author mac
 */
@Controller
public class ListingController {

    @Autowired
    ListingService listingService;

    @Autowired
    BidService bidService;

    @Autowired
    PurchaseService pService;

    @Autowired
    UserDetailsServiceImpl userService;

    @Autowired
    StatusService statusService;

    @Autowired
    ShoeModelService shoeModelService;

    @Autowired
    SizeService sizeService;

    @Autowired
    TypeService typeService;

    @Autowired
    ShoeConditionService shoeConditionService;

    @GetMapping("/listing/{id}")
    public String displayListing(@PathVariable Integer id, Model model) {
        Listing toDisplay = listingService.getListingById(id);

        model.addAttribute("listing", toDisplay);

        List<Bid> allBidsOfListing = bidService.getAllBidsOfListing(toDisplay);

        BigDecimal minStartingBid = toDisplay.getMinStartingPrice();

        BigDecimal highestBid = allBidsOfListing.stream().map(bid
                -> bid.getBidPrice()).max(BigDecimal::compareTo).orElse(minStartingBid);

        model.addAttribute("highestBid", highestBid);

        return "SpecificShoe"; //always return a template but not for a specific shoe.
    }

    @GetMapping("sell")
    public String displayAddListing(Model model) {
        List<ShoeModel> shoeModels = shoeModelService.getAllModels();
        List<Size> sizes = sizeService.getAllSizes();
        List<Type> types = typeService.getAllTypes();
        List<ShoeCondition> shoeConditions = shoeConditionService.getAllShoeConditions();
        model.addAttribute("shoeModels", shoeModels);
        model.addAttribute("sizes", sizes);
        model.addAttribute("types", types);
        model.addAttribute("shoeConditions", shoeConditions);
        return "sell";
    }

    @PostMapping("addListing")
    public String addListing(Listing toAdd,
            Integer daysToList,
            Principal seller,
            MultipartFile imageFile,
            HttpServletRequest request) throws IOException {
        Status s = new Status();
        s.setId(1);
        toAdd.setStatus(s);
        toAdd.setListDate(LocalDate.now());
        toAdd.setEndDate(LocalDate.now().plusDays(daysToList));
        SiteUser user = userService.getUserByUsername(seller.getName());
        toAdd.setSeller(user);

        toAdd = listingService.addListing(toAdd);


        if (!imageFile.isEmpty()) {
            File imageFolder = new File(request.getServletContext().getRealPath("/images/"));
            if (!imageFolder.exists()) {
                imageFolder.mkdir();
            }

            String filePath = request.getServletContext().getRealPath("/images/" + toAdd.getId() + ".jpg");
            File original = new File(filePath);

            //TODO: handle the IOException properly
            imageFile.transferTo(original);

            toAdd.setPhotoPath("/images/" + toAdd.getId() + ".jpg");

            listingService.editListing(toAdd);
        }

        return "redirect:/home";
    }

    @PostMapping("addBid")
    public String addBid(HttpServletRequest request, Principal buyer) throws InvalidBidException {
        BigDecimal bid = new BigDecimal(request.getParameter("bidEntered"));
        int listingId = Integer.parseInt(request.getParameter("listingId"));

        Listing toAdd = listingService.getListingById(listingId);
        SiteUser user = userService.getUserByUsername(buyer.getName());

        Bid newBid = new Bid();
        newBid.setBidPrice(bid);
        newBid.setListing(toAdd);
        newBid.setBuyer(user);


        bidService.addBid(newBid, user);

        return "redirect:/dashboard";
    }

    @PostMapping("buyNow")
    public String addPurchase(HttpServletRequest request, Principal buyer) throws InsufficientFundsServiceException, ServiceInvalidPurchaseException {
        int listingId = Integer.parseInt(request.getParameter("listing"));
        Listing toAdd = listingService.getListingById(listingId);

        int sellerId = Integer.parseInt(request.getParameter("seller"));
        SiteUser seller = userService.getUserById(sellerId);

        LocalDate now = LocalDate.now();

        SiteUser user = userService.getUserByUsername(buyer.getName());

        BigDecimal salePrice = new BigDecimal(request.getParameter("buynowprice"));

        Purchase newP = new Purchase();
        newP.setListing(toAdd);
        newP.setSeller(seller);
        newP.setDateSold(now);
        newP.setSalePrice(salePrice);
        newP.setBuyer(user);

        
        pService.addPurchase(newP, user);
        
        return "redirect:/dashboard";
    }

}
