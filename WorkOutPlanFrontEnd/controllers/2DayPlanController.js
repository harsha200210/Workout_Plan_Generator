import {DOUBLE} from "../util/regex.js";

export let validationWeight = () => {
  if (DOUBLE.test($("#weightInput").val())) {
    return true;
  }
  return false;
}
