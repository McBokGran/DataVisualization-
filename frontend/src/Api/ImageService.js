import axios from "axios";

const CONCERTAPIBASEURL = "http://localhost:8080/api/images";

class ImageService {
  async getImagesPaginated(
    startDate,
    endDate
  ) {
    return await axios.get(
      CONCERTAPIBASEURL +
        "/parsed?startDate="+
        startDate +
        "&endDate=" +
        endDate
    );
  }

  async getMediaTypesPaginated(
    startDate,
    endDate
  ) {
    return await axios.get(
      CONCERTAPIBASEURL +
        "/mediatypes?startDate="+
        startDate +
        "&endDate=" +
        endDate
    );
  }

  async getInkUsagePaginated(
    startDate,
    endDate
  ) {
    return await axios.get(
      CONCERTAPIBASEURL +
        "/inkusage?startDate="+
        startDate +
        "&endDate=" +
        endDate
    );
  }
  async getInkTypesPaginated(
    startDate,
    endDate
  ) {
    return await axios.get(
      CONCERTAPIBASEURL +
        "/inktypes?startDate="+
        startDate +
        "&endDate=" +
        endDate
    );
  }
}

export default new ImageService();
