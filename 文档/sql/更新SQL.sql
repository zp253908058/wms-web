ALTER TABLE pda_CheckBill ADD s_id INT;
ALTER TABLE pda_RecBill ADD s_id INT;
ALTER TABLE pda_PutOnBill ADD s_id INT;
ALTER TABLE pda_CheckBill ADD remaek VARCHAR(200) NULL ;
ALTER TABLE pda_CheckBill ADD diff_reamrk VARCHAR(200) NULL ;
ALTER TABLE pda_pdBill ADD a_id INT;
ALTER TABLE pda_pdBill ADD sa_id INT;
ALTER TABLE pda_TranBill ADD sa_id INT;
ALTER TABLE pda_TranBill ADD as_outid INT;