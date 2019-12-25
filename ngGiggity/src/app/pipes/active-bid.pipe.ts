import { Pipe, PipeTransform } from '@angular/core';
import { Bid } from '../models/bid';

@Pipe({
  name: 'activeBid'
})
export class ActiveBidPipe implements PipeTransform {

  transform(bids: Bid[]): Bid[] {
    const results = [];

    bids.forEach(bid => {
      if (bid.accepted !== false) {
        results.push(bid);
      }
    });
    return results;
  }

}
