class DataParser {
  convertImageData = async (images) => {
    let arr = [];
    for (let i = 0; i < Object.keys(images).length; i++) {


      let obj = { name: new Date(images[i].name).toISOString("en-NL").split('T')[0]};
      // loop through mediatypes
      for (let j = 0; j < Object.keys(images[i].mediaTypes).length; j++) {
        let mediatype = images[i].mediaTypes;
        obj[Object.keys(mediatype)[j]] = mediatype[Object.keys(mediatype)[j]];
      }
      arr.push(obj);
    }
    return arr;
  };

  convertPrintCycleData = async (printcycles) => {
    let arr = [];
    for (let i = 0; i < Object.keys(printcycles).length; i++) {


      let obj = { name: new Date(printcycles[i].name).toISOString("en-NL").split('T')[0]};
      // loop through mediatypes
      for (let j = 0; j < Object.keys(printcycles[i].printmodes).length; j++) {
        let printmode = printcycles[i].printmodes;
        obj[Object.keys(printmode)[j]] = printmode[Object.keys(printmode)[j]];
      }
      arr.push(obj);
    }
    return arr;
  };

  convertInkUsageData = async (inkusage) => {
    let arr = [];
    for (let i = 0; i < Object.keys(inkusage).length; i++) {
      let obj = { name: new Date(inkusage[i].name).toISOString("en-NL").split('T')[0]};
      // loop through mediatypes
      for (let j = 0; j < Object.keys(inkusage[i].inkTypes).length; j++) {
        let inktype = inkusage[i].inkTypes;
        obj[Object.keys(inktype)[j]] = inktype[Object.keys(inktype)[j]];
      }
      arr.push(obj);
    }
    return arr;
  };

  convertTopTenData = async (topten) => {
    let arr = [];
    console.log("Object.keys(topten).length " + Object.keys(topten).length)
    for (let i = 0; i < Object.keys(topten).length; i++) {
      let obj = {};
      // loop through mediatypes
      for (let j = 0; j < Object.keys(topten[i].machineSquaredecimeter).length; j++) {
        let inktype = topten[i].machineSquaredecimeter;
        obj[Object.keys(inktype)[j]] = inktype[Object.keys(inktype)[j]];
      }
      arr.push(obj);
    }
    console.log(arr.sort());
    return arr;
  };

  convertMediaTypes = async (mediatypes) => {
    let arr = [];
    console.log(typeof mediatypes)
    
    for (let j = 0; j < Object.keys(mediatypes).length; j++) {
      let obj = {};
      //let inktype = topten[i].machineSquaredecimeter;
      obj[Object.keys(mediatypes)[j]] = mediatypes[Object.keys(mediatypes)[j]];
      arr.push(obj);
    }
    return arr;
  };
 
}
export default new DataParser();
