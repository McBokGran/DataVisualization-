class DateRangeGenerator {

    getCurrentWeek = async () => {
        var curr = new Date;
        var first = curr.getDate() - curr.getDay(); // First day is the day of the month - the day of the week
        var last = first + 6; // last day is the first day + 6
        let firstday = new Date(curr.setDate(first)).toISOString("en-NL").split('T')[0];
        let lastday = new Date(curr.setDate(last)).toISOString("en-NL").split('T')[0];
        return [firstday, lastday];
    };

    getCurrentMonth = async () => {
        var date = new Date;
        var firstday = new Date(date.getFullYear(), date.getMonth(), 1).toISOString("en-NL").split('T')[0];
        var lastday = new Date(date.getFullYear(), date.getMonth() + 1, 0).toISOString("en-NL").split('T')[0];
        return [firstday, lastday];
    };

    getLast3Months = async () => {
        var date = new Date;
        var firstday = new Date(date.getFullYear(), date.getMonth()-3, 1).toISOString().split('T')[0];
        var lastday = new Date(date.getFullYear(), date.getMonth(), 1).toISOString("en-NL").split('T')[0];
        console.log(firstday + ", last3months " + lastday);
        return [firstday, lastday];
    }

    getLast4Months = async () => {
        var date = new Date;
        var firstday = new Date(date.getFullYear(), date.getMonth()-4, 1).toISOString().split('T')[0];
        var lastday = new Date(date.getFullYear(), date.getMonth(), 1).toISOString("en-NL").split('T')[0];
        console.log(firstday + ", last4months " + lastday);
        return [firstday, lastday];
    }

    getLast12Months = async () => {
        var date = new Date;
        var firstday = new Date(date.getFullYear(), date.getMonth()-12, 1).toISOString().split('T')[0];
        var lastday = new Date(date.getFullYear(), date.getMonth(), 1).toISOString("en-NL").split('T')[0];
        console.log(firstday + ", test " + lastday);
        return [firstday, lastday];
    }

  }
  
export default new DateRangeGenerator();