import axios from "axios";

const CONCERTAPIBASEURL = "http://localhost:8080/api/print-cycle";

class PrintCycleService {
  async getPrintCyclesPaginated(
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

  async getTopTenMachinesPaginated(
    startDate,
    endDate
  ) {
    return await axios.get(
      CONCERTAPIBASEURL +
        "/toptenparsed?startDate="+
        startDate +
        "&endDate=" +
        endDate
    );
  }

  async getPrintModesPaginated(
    startDate,
    endDate
  ) {
    return await axios.get(
      CONCERTAPIBASEURL +
        "/printmodes?startDate="+
        startDate +
        "&endDate=" +
        endDate
    );
  }

  async getMachineIdsPaginated(
    startDate,
    endDate
  ) {
    return await axios.get(
      CONCERTAPIBASEURL +
        "/machineids?startDate="+
        startDate +
        "&endDate=" +
        endDate
    );
  }

  async getMediaTypesPerMachine(
    startDate,
    endDate
  ) {
    return await axios.get(
      CONCERTAPIBASEURL +
        "/mediatypespermachine?startDate="+
        startDate +
        "&endDate=" +
        endDate
    );
  }

  async getMediaTypes(
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
}

export default new PrintCycleService();
