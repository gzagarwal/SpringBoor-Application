package com.aqr.reader;

import org.springframework.data.repository.CrudRepository;



public interface Repository extends CrudRepository<TopOfTheDay, Long>{

	/*@Transactional
	@Modifying(clearAutomatically = true)
    @Query("UPDATE TopOfTheDay c SET c.best_bid_size = :size WHERE c.orderId = :orderId")
    int updateBestBidSIze(@Param("size") Integer size, @Param("orderId") String orderId);
	
	@Transactional
	@Modifying(clearAutomatically = true)
    @Query("UPDATE TopOfTheDay c SET c.best_Offer_size = :size WHERE c.orderId = :orderId")
    int updateBestOfferSIze(@Param("size") Integer size, @Param("orderId") String orderId);
	
	 @Query(value = "SELECT best_bid_Price,best_bid_size,best_Offer_Price,best_Offer_size,symbol FROM  TOP_DAY where symbol =:symbol",nativeQuery = true)
	List<TopOfTheDay> fetchTopRecords(@Param("symbol") String symbol);
	
	*/
	
}
